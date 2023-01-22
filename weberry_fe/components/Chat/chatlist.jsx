import React from 'react'
import Chat1 from './chat1'
import Chat2 from './chat2'
import Chat3 from './chat3'

const Chatlist = () => {
  return (
    <>
        <div className='mt-10'>
            <Chat1 />
            <Chat2 />
            <Chat3 />
            <Chat1 />
            <Chat2 />
            <Chat3 />
        </div>
    </>
  )
}

export default Chatlist