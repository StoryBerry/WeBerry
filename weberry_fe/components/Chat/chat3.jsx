import React from 'react'
import styles from '../../styles/Home.module.css'
import Image from 'next/image'



const Chat3 = (mento) => {

  return (
    <div className="ml-4 ">
      <div className='border-b-2 border-grey_light '>
        <div className='flex justify-start items-center'>
          <Image className="" src='/images/Commu-Img/2.png' width='300' height='300' />
          <div className='flex flex-col text-left ml-2 mt-3'>
            <div className='flex justify-start items-center'>
              <h5 className="text-sm font-semibold " >유관순 </h5>
              <h5 className="text-xs ml-1 text  text-berry2" >멘토 </h5>
            </div>

            <h6 className='text-xs mt-2'>▶ 오만원농장 대표</h6>
            <h6 className='text-xs '>▶ 질병 대처 상담</h6>
            <h6 className='text-xs '>▶ 상담시간 11시 ~ 18시</h6>
            <div className='buttons flex text-center text-xs mt-4 text-white '>
              <div className='border m-1 border-none rounded-md bg-berry2 p-1 w-16' >상담하기</div>
              <div className='border m-1 border-none rounded-md bg-berry2 p-1 w-16 ' >상담예약</div>
            </div>
          </div>
        </div>
      </div>

    </div>
  )
}

export default Chat3