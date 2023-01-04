import { useAtom } from 'jotai';
import React, { useState } from 'react'
import Token from '../../atoms/Token';
import Image from 'next/image';
const SignIn = () => {
  const [userid, setUserid] = useState('');
  const [password, setPassword] = useState('');
  const [token, setToken] = useAtom(Token);

  const useridHandler = (event) => setUserid(event.target.value);
  const passwordHandler = (event) => setPassword(event.target.value);
  const signinHanlder = () => {
    fetch('http://localhost:8090/auth/sign-in',
      {
        method: 'POST',
        headers: { 'Content-Type': 'application/json; charset=UTF-8' },
        body: JSON.stringify({ 'userid': userid, 'password': password })
      })
      .then(response => response.json())
      .then(data => setToken(data))
      .catch(err => console.error(err));
  }

  return (
    <div className='bg-black_600 '>
      <div className='m-9 mt-40 content-center  bg-white border-2 rounded-lg'>
        {/* 아이디 */}
        <div className='flex justify-center mt-52'>
          <label htmlFor="" className="userid" >
            <svg className='h-5 w-5 mr- m-1' xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512">
              <path d="M256 288c79.5 0 144-64.5 144-144S335.5 0 256 0S112 64.5 112 144s64.5 144 144 144zm-94.7 32C72.2 320 0 392.2 0 481.3c0 17 13.8 30.7 30.7 30.7H481.3c17 0 30.7-13.8 30.7-30.7C512 392.2 439.8 320 350.7 320H161.3z" />
            </svg>
          </label>
          <input type="text" className="pl-1 userid border" onChange={useridHandler} />
        </div>

        {/* 비밀번호 */}
        <div className='flex justify-center m-3' >
          <label htmlFor="" className="password">
            <svg className='h-5 w-5 mr-1 m-1' xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512">
              <path d="M144 144v48H304V144c0-44.2-35.8-80-80-80s-80 35.8-80 80zM80 192V144C80 64.5 144.5 0 224 0s144 64.5 144 144v48h16c35.3 0 64 28.7 64 64V448c0 35.3-28.7 64-64 64H64c-35.3 0-64-28.7-64-64V256c0-35.3 28.7-64 64-64H80z" />
            </svg>
          </label>
          <input type="password" className="pl-1 password border" onChange={passwordHandler} />
        </div>

        <div className='buttons flex justify-center m-4 text-xs text-white font-black '>
          <div className='border m-1 border-none rounded-md bg-berry2 p-2 w-20' onClick={signinHanlder}>로그인</div>
          <div className='border m-1 p-2 w-20 border-none rounded-md  bg-berry2' >회원가입</div>
        </div>
      </div>
    </div>
  )
}

export default SignIn