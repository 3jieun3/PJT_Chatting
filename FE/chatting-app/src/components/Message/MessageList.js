import React from 'react';
import randomColor from '../../App'

function MessageList({ messages, currentUser }) {
  
  const renderMessage = (msg) => {
    const { sender, content } = msg;
    const className = (currentUser.name === msg.sender) ? "Messages-message currentUser" : "Messages-message";
    const color = (currentUser.name === msg.sender) ? currentUser.color : randomColor();
    
    return (
      <li className={ className }>
        <span className="avatar" style={{ backgroundColor: color }}></span>
        <div className="Message-content">
          <div className="username">{ sender }</div>
          <div className="text">{ content }</div>
        </div>
      </li>
    );
  };

  return (
    <ul className='message-list'>
      { messages.map(msg => renderMessage(msg)) }
    </ul>
  );
}

export default MessageList;