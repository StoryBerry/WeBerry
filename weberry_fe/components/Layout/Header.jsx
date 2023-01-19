import React from 'react'
import Link from 'next/link'
import Image from 'next/image';
import logo_gub from './logoimg.svg';
import Sidebar from './Sidebar';
import BellWarrn from './BellWarrn';
export default function Header({ }) {
  return (
    <div >
      <div className='bg-white fixed w-full top-0'>
        <div className=" mx-auto max-w-5xl px-2 sm:px-6 flex items-center justify-between py-3 border-b-2 border-y-grey border-opacity-50">
          <div className=" lg:flex-1 mt-2 ml-4 ">
            <Image src={logo_gub} width={60} height={20} alt="로고" />
          </div><bell_warring />
          <div className=' right-2 flex mt-1 px-2 sm:px-6 '>
            <BellWarrn />
            <Sidebar />
          </div>

        </div>
      </div>

    </div>
  )
}
