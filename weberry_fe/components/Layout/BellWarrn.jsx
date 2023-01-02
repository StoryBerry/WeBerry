import React, { useState } from 'react'
import Image from 'next/image'
export default function BellWarrn() {

    const [isOpen, setIsOpen] = useState(false)

    return (
        <>
            {!isOpen ?
                (
                    <button className=' mr-3' onClick={() => setIsOpen(!isOpen)}>
                        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-5 h-5">
                            <path fill-rule="evenodd" d="M5.25 9a6.75 6.75 0 0113.5 0v.75c0 2.123.8 4.057 2.118 5.52a.75.75 0 01-.297 1.206c-1.544.57-3.16.99-4.831 1.243a3.75 3.75 0 11-7.48 0 24.585 24.585 0 01-4.831-1.244.75.75 0 01-.298-1.205A8.217 8.217 0 005.25 9.75V9zm4.502 8.9a2.25 2.25 0 104.496 0 25.057 25.057 0 01-4.496 0z" clip-rule="evenodd" />
                        </svg>
                    </button>
                ) :
                (
                    <button className=' text-xl mr-3 '
                        onClick={() => setIsOpen(!isOpen)}>
                        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-5 h-5">
                            <path fill-rule="evenodd" d="M5.25 9a6.75 6.75 0 0113.5 0v.75c0 2.123.8 4.057 2.118 5.52a.75.75 0 01-.297 1.206c-1.544.57-3.16.99-4.831 1.243a3.75 3.75 0 11-7.48 0 24.585 24.585 0 01-4.831-1.244.75.75 0 01-.298-1.205A8.217 8.217 0 005.25 9.75V9zm4.502 8.9a2.25 2.25 0 104.496 0 25.057 25.057 0 01-4.496 0z" clip-rule="evenodd" />
                        </svg>
                    </button>
                )
            }
            < div className={`right-0 fixed bg-grey bg-opacity-80 w-full mt-9 h-full p-10 
              ${isOpen ? 'translate-x-0' : 'translate-x-full'} ease-in-out duration-300`}>
                <div className='text-white'>
                    <div className=' bg-black_600 justify-center ml-80 mr-80 '>
                        <a>이것이이것은 유사 재난 문자라는 것이다 엣헴! 렛잇고!~~~~ 이것은 유사 재난 문자라는 것이다 엣헴! 렛잇고!~~~~ 이것은 유사 재난 문자라는 것이다 엣헴! 렛잇고!~~~~ 것은 유사 재난 문자라는 것이다 엣헴! 렛잇고!~~~~ 이것은 유사 재난 문자라는 것이다 엣헴! 렛잇고!~~~~ 이것은 유사 재난 문자라는 것이다 엣헴! 렛잇고!~~~~ 은 유사 재난 문자라는 것이다 엣헴! 렛잇고!~~~~ 파이널판톼지폴팅~
                        </a>
                    </div>
                </div>
                <h3 className='text-xl top-56'> 지금 알림 바가 구리다고 저를 까는겁니까?</h3>
                <h3 className=' text-xl top-56'>두유 노우 아씨엔?</h3>
            </div>


        </>
    )
}
