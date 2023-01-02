import React from 'react'
import Image from 'next/image';
const Body = () => {
  return (
    <div>
      <Image className='pt-12' src={'/bg3.png'} width={800} height={30} alt="배경" />

      <div className='m-0 p-7 pt-0 text-sm'>
        <div className='-ml-7 -mr-7  mb-3  rounded-t-xl border-t-2 border-grey' >
          <details>
            <summary className='text-black_600 mt-8 mb-1 mr-32 ml-6 text-lg font-black'>
              <span>데일리리포트</span>   <span className='text-berry text-right'>D+1</span>
            </summary>

            <div className='flex justify-center'>
              <div className='m-2 bg-yello_100  max-w-7xl ml-7 mr-7 rounded-lg'>
                <div className='p-6 box-content '>
                  <h1 className='text-base'> # 제목1 | 리포트 리포트 내용상자 </h1>
                  <a className='indent-8'>#내용 1 | 리포트 분석 결과물입니다.텍스트 길이에 따라 박스가 늘어납니다.</a>
          
                </div>
              </div>
            </div>

          </details>
        </div>
      </div>
    </div>
  )
}
export default Body