import React from 'react'
import styles from '../../styles/Home.module.css'
import Image from 'next/image'

  
  const Chat = () => {

    return (
        <>
          <div className='flex justify-start items-center mt-3 mb-3 '>
            <Image className="" src='/images/Commu-Img/2.png' width='50' height='50' alt='인천_위베리' />
            
            <h5 className="text-center text-sm font-semibold" > Billy</h5>
            
          </div>


          <div className='flex justify-end items-center mt-3 mb-3'>
            <Image className="" src='/images/Commu-Img/3.png' width='50' height='50' alt='서울_플레이농장' />
            <h5 className="text-center text-sm  font-semibold" > Playdata</h5>
          </div> 
        
        </>
     
 
    )
}


export default Chat