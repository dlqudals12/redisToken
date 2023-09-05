import { useNavigate } from "react-router-dom";
import { useState } from "react";
import axios from "axios";

export const LoginUser = () => {
  const apiUrl = process.env.PUBLIC_URL;
  const navigate = useNavigate();

  const [inputData, setInputData] = useState({
    loginId: "",
    password: "",
  });

  const onClickSaveUser = () => {
    axios
      .post(apiUrl + "/user/api/login_user", inputData)
      .then((res) => {
        if (res.data.code === "0000") {
          navigate(apiUrl + "/checkUser");
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
      <div>유저 로그인</div>
      <div>
        <input
          value={inputData.loginId}
          onChange={(e) => {
            setInputData({ ...inputData, loginId: e.target.value });
          }}
          style={{ width: "150px", height: "20px" }}
        />
        <input
          value={inputData.password}
          onChange={(e) => {
            setInputData({ ...inputData, password: e.target.value });
          }}
          style={{ width: "150px", height: "20px" }}
        />
        <button onClick={onClickSaveUser}>등록</button>
      </div>
    </>
  );
};
