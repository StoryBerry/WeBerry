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
                  <a className='indent-8'>#내용 2 | 리포트 분석 결과물입니다.텍스트 길이에 따라 박스가 늘어납니다.</a>
                  <a className='indent-8'>#내용 3 | 리포트 분석 결과물입니다.텍스트 길이에 따라 박스가 늘어납니다.</a>
                </div>
              </div>
            </div>
          </details>
        </div>

        <div className='text-lg `flex justify-center font-black '>
          <div className='m-8 mb-16  mt-8'>
            <svg className='text-sun' xmlns="http://www.w3.org/2000/svg" width="26" height="26" fill="currentColor" class="bi bi-brightness-high-fill" viewBox="0 0 16 16">
              <path d="M12 8a4 4 0 1 1-8 0 4 4 0 0 1 8 0zM8 0a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-1 0v-2A.5.5 0 0 1 8 0zm0 13a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-1 0v-2A.5.5 0 0 1 8 13zm8-5a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1 0-1h2a.5.5 0 0 1 .5.5zM3 8a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1 0-1h2A.5.5 0 0 1 3 8zm10.657-5.657a.5.5 0 0 1 0 .707l-1.414 1.415a.5.5 0 1 1-.707-.708l1.414-1.414a.5.5 0 0 1 .707 0zm-9.193 9.193a.5.5 0 0 1 0 .707L3.05 13.657a.5.5 0 0 1-.707-.707l1.414-1.414a.5.5 0 0 1 .707 0zm9.193 2.121a.5.5 0 0 1-.707 0l-1.414-1.414a.5.5 0 0 1 .707-.707l1.414 1.414a.5.5 0 0 1 0 .707zM4.464 4.465a.5.5 0 0 1-.707 0L2.343 3.05a.5.5 0 1 1 .707-.707l1.414 1.414a.5.5 0 0 1 0 .708z" />
            </svg>
            온도
            <span className='ml-5 border-2  border-grey rounded-md p-4 text-berry '>온도숫자</span>
          </div>

          <div className='m-8 '>
            <svg className='text-water' xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512" width="20" height="20" fill="currentColor"><path d="M64 0C55.1 0 46.6 3.7 40.6 10.2s-9.1 15.2-8.5 24.1L60.9 437.7c3 41.9 37.8 74.3 79.8 74.3H307.3c42 0 76.8-32.4 79.8-74.3L415.9 34.3c.6-8.9-2.4-17.6-8.5-24.1S392.9 0 384 0H64zm41 156.5L98.4 64H349.6L343 156.5l-24.2 12.1c-19.4 9.7-42.2 9.7-61.6 0c-20.9-10.4-45.5-10.4-66.4 0c-19.4 9.7-42.2 9.7-61.6 0L105 156.5z" /></svg>
            습도
            <span className='ml-5 border-2  border-grey rounded-md p-4 text-water'> 습도숫자 </span></div>
        </div>

        {/* 
        <div className='m-10'>
          <span>온도</span><div className='border-2 rounded-md p-4 -mb-4  m-10'>  온도 </div>
          <div className='border-2 rounded-md p-4 m-10'>습도</div>
        </div> */}
      </div>
    </div>
  )
}
export default Body