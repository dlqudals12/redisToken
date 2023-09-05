import logo from "./logo.svg";
import "./App.css";
import { SaveUser } from "./component/SaveUser";
import { LoginUser } from "./component/LoginUser";
import { CheckUser } from "./component/CheckUser";
import { Route, Routes } from "react-router-dom";

function App() {
  const defaultUrl = process.env.PUBLIC_URL;
  return (
    <>
      <Routes>
        <Route path={defaultUrl + "/"} element={<SaveUser />} />
        <Route path={defaultUrl + "/loginUser"} element={<LoginUser />} />
        <Route path={defaultUrl + "/checkUser"} element={<CheckUser />} />
      </Routes>
    </>
  );
}

export default App;
