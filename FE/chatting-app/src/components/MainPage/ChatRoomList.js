import { Card, Typography } from "@material-ui/core";

const ChatRoomList = ({ chatRooms }) => {

	const handleOnJoinChatRoom = () => {
		
	};
	
	const renderChatRoom = (room, idx) => {
		return (
			<Card className="chat-room-card" key={idx}
				onClick={ handleOnJoinChatRoom }>
				<Typography component="h5" variant="h5">
					{ room.roomName }
        </Typography>
        <Typography variant="subtitle1" color="textSecondary">
					{ room.roomId }
        </Typography>
			</Card>
		);
	};

	return (
		<div className='chat-room-list'>
			{ chatRooms.map((room, idx) => renderChatRoom(room, idx)) }
		</div>
	);

};

export default ChatRoomList;