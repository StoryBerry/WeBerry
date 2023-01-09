import React, { useEffect } from "react";
import Link from "next/link";
import Image from "next/image";
import logo_gub from "./logoimg.svg";
import Sidebar from "./Sidebar";
import BellWarrn from "./BellWarrn";
import { useAtom } from "jotai";
import Token from "../../atoms/Token";
export default function Header({}) {
  const [token, setToken] = useAtom(Token);

  const connectToAlert = () => {
    const url = new URL('wss://localhost:8090/alert');
    const socket = new WebSocket(url);
    console.log(socket);
    console.log("connectToAlert");
    socket.onopen = () => {
      console.log('connected!');
      socket.send({'header': {'Authorization': token.token}});
    }
  }

  token && connectToAlert();
  useEffect(() => {

  }, [])

  return (
    <div>
      <div className="bg-white fixed w-full top-0 z-40">
        <div className=" mx-auto max-w-5xl px-2 sm:px-6 flex items-center justify-between py-3 border-b-2 border-y-grey border-opacity-50">
          <Link href="/">
            <div className=" lg:flex-1 mt-2 ml-4 ">
              <Image src={logo_gub} width={60} height={20} alt="로고" />
            </div>
          </Link>
          <div className=" right-2 flex mt-1 px-2 sm:px-6 ">
            <BellWarrn />
            <Sidebar />
          </div>
        </div>
      </div>
    </div>
  );
}
