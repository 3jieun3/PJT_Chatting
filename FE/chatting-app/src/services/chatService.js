
const chatService = {

  onConnected: () => {
    console.log("Connection Successfully!");
  },

  onDisconnected: () => {
    console.log("Connection Failed!");
  },

  sendMessage: (currentUser, sendText, clientRef) => {
    let send_message = {
      sender: currentUser.name,
      content: sendText,
      roomId: "5b34af66-9f82-47dc-b6a0-0260b48deb75"
    }
    clientRef.current.sendMessage("/pub/message", JSON.stringify(send_message));
  }

};

export default chatService;