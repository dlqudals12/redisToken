import Axios from "axios";
const axios = Axios.create();
const getValueInCookies = (name) => {
  const cookies = document.cookie.split(";");
  for (let i = 0; i < cookies.length; i++) {
    if (cookies[i].includes(`${name}=`)) {
      return cookies[i].split("=")[1];
    }
  }

  return "";
};

axios.interceptors.request.use((config) => {
  const accessToken = getValueInCookies("accessToken");

  if (accessToken !== "") {
    const header = `bearer ${accessToken}`;
    config.headers.Authorization = header;
  } else {
    delete config.headers.Authorization;
  }
  return config;
});

axios.interceptors.response.use(
  (response) => {
    return response;
  },
  async (error) => {
    if (error.response.status === 400) {
      document.cookie = `accessToken=;max-age=0;path=${process.env.PUBLIC_URL}`;
      document.cookie = `refreshToken=;max-age=0;path=${process.env.PUBLIC_URL}`;
      document.cookie = `productCheck=;max-age=0;path=${process.env.PUBLIC_URL}`;
      window.location.href = process.env.PUBLIC_URL + "/";
    }
  }
);
export { Axios, axios };
