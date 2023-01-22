import { useAtom } from "jotai";
import React, { useEffect } from "react";
import Token from "../../../atoms/Token";

const index = () => {
  const [token, setToken] = useAtom(Token);

  useEffect(() => {
    fetch("http://localhost:8090/auth/sign-in", {
      method: "POST",
      headers: { "Content-Type": "application/json; charset=UTF-8" },
      body: JSON.stringify({
        userid: "groupmtter",
        password: "1234",
      }),
    })
      .then((response) => response.json())
      .then((data) => setToken(data.token))
      .catch((err) => console.error(err));
  }, []);

  return <div>index</div>;
};

export default index;
