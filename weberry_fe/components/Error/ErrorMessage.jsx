import React from 'react'

const ErrorMessage = (props) => {
  const err = props.err;

  return (
    <>
    <div className='fixed bg-white z-20 mt-24 w-4/5 left-9 content-center'>
      <div className="error-message">{err}</div>
      <div className='buttons flex w-4/5 justify-around mx-8 my-4'>
        <div className="button w-16 p-2 bg-water text-black font-semibold rounded-lg shadow-md" >작성</div>
        <div className="button w-16 p-2 bg-grey text-black font-semibold rounded-lg shadow-md" >취소</div>
      </div>
    </div>
    </>
  )
}

export default ErrorMessage