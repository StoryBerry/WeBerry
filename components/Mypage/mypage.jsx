import React from 'react'

const mypage = () => {
    return (

        <div className='mt-40 m-8 border-2 border-white bg-white rounded-md ' >
            <div className=''>
                <div className=" m-8">
                    <img
                        className="mt-10 m-4 mb-3 inline-block h-20 w-20 rounded-full ring-2 ring-white border-4  border-grey"
                        src="https://images.unsplash.com/photo-1491528323818-fdd1faba62cc?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80"
                        alt=""
                    />
                    <div>
                        <h1 className='mt-1 text-base font-black' >🍓홍길동🍓</h1>
                        <h6 className='pb-4 text-xs text-black_600'>@sample_1</h6>
                        <h2 className=' text-xs p-2'>"서울에서 싱싱한 딸기 농사를 합니다. 아주 맛있쏘요"</h2>
                        <div className='flex justify-center mt-6 text-xs '>
                            <svg className='text-black_600 pr-1  bi bi-geo-alt-fill' xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
                                <path d="M8 16s6-5.686 6-10A6 6 0 0 0 2 6c0 4.314 6 10 6 10zm0-7a3 3 0 1 1 0-6 3 3 0 0 1 0 6z" />
                            </svg>
                            서울 금천구 가산디지털1로 25 </div>
                        <h5 className=' text-xs'> 플레이농장 </h5>
                        <button><div className='mt-10  mb-12 w-52 p-1 text-xs bg-grey  rounded-md  border-2 border-grey'>프로필 수정</div></button>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default mypage