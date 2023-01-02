import React from 'react'
import Link from 'next/link'
import Image from 'next/image';
import home from './home.svg';
import comu from './conum.svg';
import tailk from './tail.svg'
import person from './person.svg'
const Footer = () => {
  return (
    <div className='bg-white fixed w-full bottom-0 '>
      <div className=" mx-auto max-w-5xl px-4 sm:px-6 py-3 border-t-2 border-y-grey border-opacity-50">
        <div className="place-content-evenly flex " >

        <Link href="/">
          <button className=' hover:text-light_pink focus:text-light_pink'>
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-5 h-5">
              <path d="M11.47 3.84a.75.75 0 011.06 0l8.69 8.69a.75.75 0 101.06-1.06l-8.689-8.69a2.25 2.25 0 00-3.182 0l-8.69 8.69a.75.75 0 001.061 1.06l8.69-8.69z" />
              <path d="M12 5.432l8.159 8.159c.03.03.06.058.091.086v6.198c0 1.035-.84 1.875-1.875 1.875H15a.75.75 0 01-.75-.75v-4.5a.75.75 0 00-.75-.75h-3a.75.75 0 00-.75.75V21a.75.75 0 01-.75.75H5.625a1.875 1.875 0 01-1.875-1.875v-6.198a2.29 2.29 0 00.091-.086L12 5.43z" />
            </svg>
          </button> </Link>

        <Link href="/commu/cindex">
          <button className=' hover:text-light_pink focus:text-light_pink' xmlns="http://www.w3.org/2000/svg" href='pages/commu/cindex.js'>
            <svg  viewBox="0 0 24 24" fill="currentColor" class="w-5 h-5">
              <path d="M4.5 3.75a3 3 0 00-3 3v.75h21v-.75a3 3 0 00-3-3h-15z" />
              <path fill-rule="evenodd" d="M22.5 9.75h-21v7.5a3 3 0 003 3h15a3 3 0 003-3v-7.5zm-18 3.75a.75.75 0 01.75-.75h6a.75.75 0 010 1.5h-6a.75.75 0 01-.75-.75zm.75 2.25a.75.75 0 000 1.5h3a.75.75 0 000-1.5h-3z" clip-rule="evenodd" />
            </svg>
          </button></Link>

          <button className=' hover:text-light_pink focus:text-light_pink'>
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-5 h-5">
              <path fill-rule="evenodd" d="M4.848 2.771A49.144 49.144 0 0112 2.25c2.43 0 4.817.178 7.152.52 1.978.292 3.348 2.024 3.348 3.97v6.02c0 1.946-1.37 3.678-3.348 3.97a48.901 48.901 0 01-3.476.383.39.39 0 00-.297.17l-2.755 4.133a.75.75 0 01-1.248 0l-2.755-4.133a.39.39 0 00-.297-.17 48.9 48.9 0 01-3.476-.384c-1.978-.29-3.348-2.024-3.348-3.97V6.741c0-1.946 1.37-3.68 3.348-3.97z" clip-rule="evenodd" />
            </svg>

          </button>

          <button className=' hover:text-light_pink focus:text-light_pink'>
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-5 h-5">
              <path fill-rule="evenodd" d="M7.5 6a4.5 4.5 0 119 0 4.5 4.5 0 01-9 0zM3.751 20.105a8.25 8.25 0 0116.498 0 .75.75 0 01-.437.695A18.683 18.683 0 0112 22.5c-2.786 0-5.433-.608-7.812-1.7a.75.75 0 01-.437-.695z" clip-rule="evenodd" />
            </svg>
          </button>

          {/* 
          버튼호버 에러 
          테일윈드 컨

          <button className=' hover:text-light_pink focus:text-light_pink'>
          <Image className='hover:fill-light_pink' src={home} width={25} height={25} /></button>
          <Image src={comu} width={25} height={25} />
          <Image src={tailk} width={25} height={25} />
          <Image src={person} width={25} height={25} /> */}
        </div></div>
      {/*       
      <div className='container max-w-lg px-4 py-32 mx-auto mt-30 text-left md:max-w-none md:text-center tails-selected-element m-10 mb-32'>
        <h1>기재되어있는 회사명 · 제품명 · 시스템 이름은 해당 소유자의 상표 또는 등록 상표입니다.</h1>
        <h1>© 2023 - 2025 WeBerry CO. LTD. All Rights Reserved. Published in Korea by WeBerry SOFT CO. LTD.</h1>
        <br></br>
        <h1>개인정보 처리방침(링크)</h1>
      </div> */}
    </div>

  )
}

export default Footer