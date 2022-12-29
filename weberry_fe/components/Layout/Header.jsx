import React from 'react'
import Link from 'next/link'
import Image from 'next/image';
import logo_gub from './logoimg.svg';
import more from './more.svg';
import bell from './bell.svg';
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
            {/* <a>
              <Image className='mr-4 mt-0.5' src={bell} width={20} height={15} alt="알림" />
            </a> */}
            <BellWarrn />
            <Sidebar />
          </div>

        </div>
      </div>

    </div>
  )
}

{/* <Link href="/info"><div className=" font-GmarketSansMedium cursor-pointer text-white font-medium"> 공지사항 </div></Link>
          <Link href="#" ><div className="font-GmarketSansMedium cursor-pointer text-white font-medium"> 새싹가이드</div></Link> */}

