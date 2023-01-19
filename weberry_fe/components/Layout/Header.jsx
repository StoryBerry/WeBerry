import React, { useEffect, useState } from "react";
import Link from "next/link";
import Image from "next/image";
import logo_gub from "./logoimg.svg";
import Sidebar from "./Sidebar";
import BellWarrn from "./BellWarrn";
import { useAtom } from "jotai";
import Token from "../../atoms/Token";
export default function Header({}) {
  const [token, setToken] = useAtom(Token);
  const [message, setMessage] = useState(null);
  const [messages, setMessages] = useState([]);
  const [connected, setConnected] = useState(false);

  const connectToAlert = () => {
    const socket = new WebSocket("ws://localhost:8090/alert");
    socket.onopen = () => {
      console.log("Connected!");
      socket.send(token.token);
    };
    socket.onerror = () => console.log("연결안됨");
    socket.addEventListener("message", (event) => setMessage(event.data));
  };

  const alertMessage = (message) => {
    if (message === "WeBerry에 오신 것을 환영합니다.") {
      if (!connected) {
        alert(message);
        setConnected(true);
      }
    } else {
      alert(message);
      setMessages([...messages, message]);
    }
  };

  useEffect(() => {
    token && connectToAlert();
    message && alertMessage(message);
  }, [token, message]);

  return (
    <div>
      <div className="bg-white fixed w-full top-0 z-40">
        <div className=" mx-auto max-w-5xl px-2 sm:px-6 flex items-center justify-between py-3 border-b-2 border-y-grey border-opacity-50 h-20">
          <Link href="/">
            <div className=" lg:flex-1 mt-2 ml-4 ">
              <Image src={logo_gub} width={70} height={20} alt="로고" />
            </div>
          </Link>
          <div className=" right-2 flex mt-1 px-2 sm:px-6 ">
            <BellWarrn messages={messages} />
            <Sidebar />
          </div>
        </div>
      </div>
    </div>
  );
}
