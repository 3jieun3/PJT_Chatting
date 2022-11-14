import React, { useState, useRef } from "react";
import SockJsClient from "react-stomp";

import "./App.css";

import LoginForm from "./components/Login/LoginForm";
import InputForm from "./components/Input/InputForm";
import MessageList from "./components/Message/MessageList";

// import chatAPI from "./services/chatApi";
import { randomColor } from "./utils/common";


function App() {

  const [user, setUser] = useState(null);
  const [messages] = useState([]);

  const $websocket = useRef(null);

  const handleLoginSubmit = (name) => {
    setUser({ name: name, color: randomColor() });
    console.log(name, ": 로그인 성공!");
  };

  const handleSendMessage = (sendText) => {
    console.log(user.name + " 메시지 전송 : " + sendText);
    let send_message = {
      sender: user.name,
      content: sendText,
      roomId: "3124e7b4-2a57-4412-8c71-e902d10ab342"
    }
    $websocket.current.sendMessage("/pub/message", JSON.stringify(send_message));
  };

  return (
    <div className="App">
      { user !== null ? (
          <div className="chat-container">
            <SockJsClient
               url="http://localhost:8080/kafka/je-chat"
               topics={["/topic/room/APPLE_CHAT"]}    
               onMessage={msg => console.log(msg)}  
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