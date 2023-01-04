import { useAtom } from 'jotai';
import React, { useState } from 'react'
import Token from '../../atoms/Token';

const SignIn = () => {
  const [userid, setUserid] = useState('');
  const [password, setPassword] = useState('');
  const [token, setToken] = useAtom(Token);

  const useridHandler = (event) => setUserid(event.target.value);
  const passwordHandler = (event) => setPassword(event.target.value);
  const signinHanlder = () => {
    fetch('http://localhost:8090/auth/sign-in',
          {method: 'POST',
           headers: {'Content-Type': 'application/json; charset=UTF-8'},
           body: JSON.stringify({'userid': userid, 'password': password})})
         .then(response => response.json())
         .then(data => setToken(data))
         .catch(err => console.error(err));
  }

  return (
    <div className='mt-20 w-4/5 content-center'>
      <div className=' '>
        <div>
          <label htmlFor="" className="userid">아이디</label>
          <input type="text" className="userid border" onChange={useridHandler}/>
        </div>
        <div>
          <label htmlFor="" className="password">비밀번호</label>
          <input type="password" className="password border" onChange={passwordHandler}/>
        </div>
        <div className='buttons flex justify-arround'>
          <div onClick={signinHanlder}>로그인</div>
          <div>회원가입</div>
        </div>
      </div>
    </div>
  )
}

export default SignIn