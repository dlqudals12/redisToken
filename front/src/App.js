import logo from "./logo.svg";
import "./App.css";
import { SaveUser } from "./component/SaveUser";
import { LoginUser } from "./component/LoginUser";
import { CheckUser } from "./component/CheckUser";
import { Route, Routes } from "react-router-dom";
import {useCookies} from "react-cookie";
import {useEffect} from "react";
import axios from "axios";
import moment from "moment";

function App() {
  const apiUrl = process.env.PUBLIC_URL;
  const [cookies, setCookies, removeCookies] = useCookies(['userIp']);

  useEffect(() => {
      if(!cookies.userIp) {
          axios.get('https://geolocation-db.com/json/').then(res => {
              setCookies("userIp", res.data.IPv4, {
                  path: apiUrl,
                  maxAge: 100000000
              });
          })
      }
  }, []);

  console.log( moment().add(100, 'years').format("YYYY-MM-DD"))

  return (
    <>
      <Routes>
        <Route path={apiUrl + "/"} element={<LoginUser />} />
        <Route path={apiUrl + "/saveUser"} element={<SaveUser />} />
        <Route path={apiUrl + "/checkUser"} element={<CheckUser />} />
      </Routes>
    </>
  );
}

export default App;
