import React from 'react'
import Image from 'next/image';

const Body = () => {
  return (
    <div>
      <Image className='pt-12' src={'/bg3.png'} width={800} height={30} alt="배경" />
      
      <div className='m-0 p-7 pt-0 text-sm'>
        <div className='-ml-7 -mr-7 border-t-2 mb-3  rounded-t-xl border-grey' >
          <details>
            <summary className='text-black_600 mt-8 mb-1 mr-32 ml-6 text-lg font-black'>
              <span>데일리리포트</span>   <span className='text-berry text-right'>D+1</span>
            </summary>

            <div className='m-2 bg-yello_100 h-52  w-80 ml-7 mr-7 rounded-lg'>
              <div className='p-2 box-content h-48 w-58  border-2 ...  items-baseline'>
              리포트 내용상자
              </div>
            </div>

          </details>
        </div>
      </div>
    </div>
  )
}
export default Body