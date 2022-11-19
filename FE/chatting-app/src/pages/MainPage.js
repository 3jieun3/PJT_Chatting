import React, { useState } from "react";
import { Button } from "@material-ui/core";
import AddCircleIcon from '@material-ui/icons/AddCircle';

import ChatRoomList from "./../components/MainPage/ChatRoomList";

const MainPage = () => {

	const [ chatRooms, setChatRooms ] = useState([{ roomId: "280a8a4d-a27f-4d01-b031-2a003cc4c039", roomName: "모여라!" }, { roomId: "280a8a4d-a27f-4d01-b031-2a003cc4c039", roomName: "채팅하자!" }]);

	return (
		<div>
			<h2>Chatting Rooms</h2>
			<Button 
				variant="contained"
				color="primary"
				startIcon={<AddCircleIcon />}
			>
				채팅방 만들기
			</Button>
			<ChatRoomList chatRooms = { chatRooms } />
		</div>
	);
};

export default MainPage;

//   const [user, setUser] = useState(null);
//   const [messages, setMessage] = useState([]);
  
//   const $websocket = useRef(null);

//   const handleLoginSubmit = (name) => {
//     setUser({ name: name, color: randomColor() });
//     console.log(name, ": 로그인 성공!");
//   };

//   const handleSendMessage = (sendText) => {
//     console.log("메시지 전송 : " + sendText);
//     chatService.sendMessage(user, sendText, $websocket);
//   };

//   const handleReceiveMessage = (receivedMsg) => {
//     console.log("메시지 수신 : " + receivedMsg);
//     setMessage(messages.concat(receivedMsg));
//   };

//   return (
//     <div className="App">
//       { user !== null ? (
//           <div className="chat-container">
//             <Button variant="contained" color="primary" onClick={chatService.requestChat(user, $websocket)}>Go Chat!</Button>

//             <SockJsClient
//                url="http://localhost:8080/kafka/je-chat"
//                topics={["/topic/room/APPLE_CHAT"]}
//                onConnect={chatService.onConnected}
//                onDisconnect={chatService.onDisconnected}    
//                onMessage={handleReceiveMessage}  
//                ref={$websocket}/>
//             <MessageList messages={messages} currentUser={user}/>
//             <InputForm handleOnSendMessage={handleSendMessage}/>
//           </div>
//         ) :
//         ( <LoginForm handleOnLoginSubmit={handleLoginSubmit}/> )
//       }
//     </div>
//   );
// }
