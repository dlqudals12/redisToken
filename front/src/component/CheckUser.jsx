import { useEffect, useState } from "react";
import { axios } from "../util/axios";
import { useCookies } from "react-cookie";

export const CheckUser = () => {
  const apiUrl = process.env.PUBLIC_URL;

  const [cookies, setCookies, removeCookies] = useCookies("accessToken");

  console.log(cookies);
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

  return (
    <>
      <div>유저 체크</div>
      <button onClick={onClickPost}>새로고침</button>
    </>
  );
};
