import React, { useEffect, useState } from "react";
import Image from "next/image";
import Link from "next/link";
export default function BellWarrn(props) {
  const messages = props.messages;

  const [isOpen, setIsOpen] = useState(false);
  const [unread, setUnread] = useState(0);

  const readHandler = () => {
    setUnread(0);
    setIsOpen(!isOpen);
  };

  useEffect(() => {
    setUnread(messages.length);
  }, [messages]);

  return (
    <>
      {!isOpen ? (
        <button className=" mr-3 relative" onClick={readHandler}>
          {unread > 0 && (
            <div className="alert rounded-2xl border w-5 h-5 -right-2 -top-1 bg-berry absolute text-sm font-bold text-white text-center">
              {unread}
            </div>
          )}
          <svg
            className="w-8 h-8"
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 24 24"
            fill="currentColor">
            <path
              fillRule="evenodd"
              d="M5.25 9a6.75 6.75 0 0113.5 0v.75c0 2.123.8 4.057 2.118 5.52a.75.75 0 01-.297 1.206c-1.544.57-3.16.99-4.831 1.243a3.75 3.75 0 11-7.48 0 24.585 24.585 0 01-4.831-1.244.75.75 0 01-.298-1.205A8.217 8.217 0 005.25 9.75V9zm4.502 8.9a2.25 2.25 0 104.496 0 25.057 25.057 0 01-4.496 0z"
              clipRule="evenodd"
            />
          </svg>
        </button>
      ) : (
        <button className=" text-xl mr-3 " onClick={() => setIsOpen(!isOpen)}>
          <svg
            className="w-8 h-8"
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 24 24"
            fill="currentColor">
            <path
              fillRule="evenodd"
              d="M5.25 9a6.75 6.75 0 0113.5 0v.75c0 2.123.8 4.057 2.118 5.52a.75.75 0 01-.297 1.206c-1.544.57-3.16.99-4.831 1.243a3.75 3.75 0 11-7.48 0 24.585 24.585 0 01-4.831-1.244.75.75 0 01-.298-1.205A8.217 8.217 0 005.25 9.75V9zm4.502 8.9a2.25 2.25 0 104.496 0 25.057 25.057 0 01-4.496 0z"
              clipRule="evenodd"
            />
          </svg>
        </button>
      )}
      <div
        className={`right-0 fixed bg-grey  w-full mt-9 h-full p-10 
              ${
                isOpen ? "translate-x-0" : "translate-x-full"
              } ease-in-out duration-300`}
        onClick={() => setIsOpen(!isOpen)}>
        <h1 className="text-xl font-black"> 알림</h1>

        {messages.map((message, idx) => {
          return (
            <Link key={idx} href="/">
              <div className="flex justify-center w-full mt-3 mb-5">
                <div className="  bg-black_600 bg-opacity-70 rounded-lg shadow-lg flex flex-wrap w-80">
                  <h6 className="text-left pl-4 pt-2 text-xs text-white display flex-auto">
                    인천_위베리
                  </h6>
                  <h6 className="text-right pr-4 pt-2 text-xs text-white flex-auto">
                    1분전
                  </h6>
                  <div className="text-left  p-4 text-sm text-white flex justify-end">
                    {" #"}
                    {idx + 1}번 알림 | {message}
                  </div>
                </div>
              </div>
            </Link>
          );
        })}
      </div>
    </>
  );
}
