import { useEffect, useState } from "react";
import { axios } from "../util/axios";
import { useCookies } from "react-cookie";
import {useNavigate} from "react-router-dom";

export const CheckUser = () => {
  const apiUrl = process.env.PUBLIC_URL;
  const navigate = useNavigate();

  const [cookies, setCookies, removeCookies] = useCookies("accessToken");

  const onClickPost = () => {
    axios
      .get(apiUrl + "/check/api/check_user")
      .then((res) => {
        console.log(res.data.result);
      })
      .catch((e) => {
        console.log(e);
        alert("오류");
      });
  };

  const onClickLogout = () => {
    axios
        .post(apiUrl + "/user/api/logout_user")
        .then((res) => {
         if(res.data.code === "0000") {
           navigate(apiUrl + "/");
         } else {
           alert(res.data.msg);
         }
        })
        .catch((e) => {
          console.log(e);
          alert("오류");
        });
  }

  return (
    <>
      <div>유저 체크</div>
      <div><button onClick={onClickPost}>새로고침</button></div>
      <div><button onClick={onClickLogout}>로그아웃</button></div>
    </>
  );
};
