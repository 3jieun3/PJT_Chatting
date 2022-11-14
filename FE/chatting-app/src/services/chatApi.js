import Axios from "axios";

const api = Axios.create({
  baseURL: "http://localhost:8080/kafka/je-chat"
});

const chatAPI = {
  sendMessage: (currentUser, text) => {
    let sendMsg = {
      sender: currentUser.name,
      content: text,
      roomId: "910c8a65-4b10-41f2-9137-9e3bebccac95"
    };
    return api.post('/pub/message', sendMsg);
  }
};

export default chatAPI;