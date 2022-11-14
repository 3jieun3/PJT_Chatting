import React, { useState } from "react";
// import SockJsClient from "react-stomp";

import "./App.css";

import chatAPI from "./services/chatApi";
import LoginForm from "./components/Login/LoginForm";
import InputForm from "./components/Input/InputForm";
import MessageList from "./components/Message/MessageList";

function App() {
  const [user, setUser] = useState(null);
  const [messages, setMessages] = useState([]);

  const handleLoginSubmit = (name) => {
    setUser({ name: name, color: randomColor() });
    console.log(name, " : 로그인 성공!");
  };

  const randomColor = () => {
    return "#" + Math.floor(Math.random() * 0xffffff).toString(16);
  };

  const handleSendMessage = (sendMsg) => {
    console.log(user.name + " 메시지 전송 : " + sendMsg);
    chatAPI.sendMessage(user, sendMsg).then(
      res => console.log("전송 성공! ", res)
    ).catch(err => console.log("전송 실패!"))
  };

  return (
    <div className="App">
      { user !== null ? (
          <div className="chat-container">
            {/* <SockJsClient
              url={"http://localhost:8080/kafka/je-chat"}
              topics={["/topic/room"]}
              onConnect={console.log("Connected!")}
              onDisconnect={console.log("Disconnected!")}
              onMessage={console.log(msg)}
              debug={false}/> */}
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