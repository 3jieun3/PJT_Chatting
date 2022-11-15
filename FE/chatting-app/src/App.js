import React, { useState, useRef } from "react";
import SockJsClient from "react-stomp";

import "./App.css";

import LoginForm from "./components/Login/LoginForm";
import InputForm from "./components/Input/InputForm";
import MessageList from "./components/Message/MessageList";

import chatService from "./services/chatService";
import { randomColor } from "./utils/common";


function App() {

  const [user, setUser] = useState(null);
  const [messages, setMessage] = useState([]);
  
  const $websocket = useRef(null);

  const handleLoginSubmit = (name) => {
    setUser({ name: name, color: randomColor() });
    console.log(name, ": 로그인 성공!");
  };

  const handleSendMessage = (sendText) => {
    console.log("메시지 전송 : " + sendText);
    chatService.sendMessage(user, sendText, $websocket);
  };

  const handleReceiveMessage = (receivedMsg) => {
    console.log("메시지 수신 : " + receivedMsg);
    setMessage(messages.concat(receivedMsg));
  };

  return (
    <div className="App">
      { user !== null ? (
          <div className="chat-container">
            <SockJsClient
               url="http://localhost:8080/kafka/je-chat"
               topics={["/topic/room/APPLE_CHAT"]}
               onConnect={chatService.onConnected}
               onDisconnect={chatService.onDisconnected}    
               onMessage={handleReceiveMessage}  
               ref={$websocket}/>
            <MessageList messages={messages} currentUser={user}/>
            <InputForm handleOnSendMessage={handleSendMessage}/>
          </div>
        ) :
        ( <LoginForm handleOnLoginSubmit={handleLoginSubmit}/> )
      }
    </div>
  );
}

export default App;