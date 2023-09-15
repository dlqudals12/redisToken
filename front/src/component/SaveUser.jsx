import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

export const SaveUser = () => {
  const apiUrl = process.env.PUBLIC_URL;
  const navigate = useNavigate();

  const [inputData, setInputData] = useState({
    loginId: "",
    password: "",
    name: "",
  });

  const onClickSaveUser = () => {
    axios
      .post(apiUrl + "/user/api/save_user", inputData)
      .then((res) => {
        if (res.data.code === "0000") {
          navigate(apiUrl + "/");
        } else {
          alert("오류");
        }
      })
      .catch((e) => {
        alert("오류");
      });
  };

  return (
    <>
      <div>유저 등록</div>
      <div>
          <label>로그인 Id</label>
        <input
          value={inputData.loginId}
          onChange={(e) => {
            setInputData({ ...inputData, loginId: e.target.value });
          }}
          style={{ width: "150px", height: "20px", marginLeft: '20px' }}
        />
        <br />
          <label>비밀번호</label>
        <input
          value={inputData.password}
          type={'password'}
          onChange={(e) => {
            setInputData({ ...inputData, password: e.target.value });
          }}
          style={{ width: "150px", height: "20px", marginLeft: '20px' }}
        />
        <br />
          <label>이름</label>
        <input
          value={inputData.name}
          onChange={(e) => {
            setInputData({ ...inputData, name: e.target.value });
          }}
          style={{ width: "150px", height: "20px", marginLeft: '20px' }}
        />
        <br />
        <button onClick={onClickSaveUser}>등록</button>
      </div>
    </>
  );
};
