import React, { useState } from "react";
import TextField from "@material-ui/core/TextField";
import Button from '@material-ui/core/Button';

function LoginForm({ handleOnLoginSubmit }) {
  const [name, setName] = useState("");

  const handleOnChange = (e) => {
    setName(e.target.value);
  };

  const handleOnSubmit = () => {
    handleOnLoginSubmit(name);
  };
  
  return (
    <div>
      <TextField 
        label="Type your Nickname"
        placeholder="사용할 닉네임을 입력하세요."
        onChange={handleOnChange}
        onSubmit={handleOnSubmit}
        margin="normal"
        onKeyPress={event => {
          if (event.key === "Enter") handleOnSubmit();
        }}/>
      <br />
      <Button variant="contained" color="primary" onClick={handleOnSubmit}>Join!</Button>
    </div>
  );
}

export default LoginForm;