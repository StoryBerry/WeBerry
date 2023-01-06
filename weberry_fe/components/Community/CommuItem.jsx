import React from 'react'
import styles from '../../styles/Home.module.css'
import Image from 'next/image'
import Link from 'next/link'

const CommuItem = (props) => {
  const post = props.post;

  return (
    <div className={styles.card_board}>
        <div className='flex justify-start items-center mt-3 mb-3'>
          <Image className="" src='/images/Commu-Img/2.png' width='50' height='50' />
          <h5 className="text-center font-semibold text-base text-opacity-5 " >{post.user.farm.farmId} </h5>
          <h5 className="text-center text-sm ml-1 " > @{post.user.nickName}</h5>
        </div>
        <img className='drop-shadow-xl rounded-xl' src='/images/strawberry.jpeg' width='350' height='194' />
   
        <h5 className= 'text-left text-xl ml-3 mt-6' >
          {post.content}
        </h5>
        <div className= 'text-left text-m ml-2 mt-6 mb-3'> comment {post.comments.length} </div>
      </div>
  )
}

export default CommuItem