import React from 'react'
import styles from '../../styles/Home.module.css'
import Image from 'next/image'
import CommuCommentList from './CommuCommentList';

const CommuItemDetail = (props) => {
  const post = props.post;

  return (

    <div className={styles.card_board}>
      <img className="drop-shadow-xl rounded-xl" src='/images/strawberry.jpeg' width='500%' height='100%' />
      <div className='flex justify-start items-center mt-3'>
        <Image className="" src='/images/Commu-Img/2.png' width='50' height='50' />
        <h5 className="text-center font-semibold text-opacity-5 " >{post.user.farm.farmId} </h5>
        <h5 className="text-center ml-1" > @{post.user.nickName}</h5>
      </div >
  
      <h5 className='text-left text-xl ml-3 mt-6' >
        {post.content}
      </h5>
      
      <div className={styles.card_button}>
        <CommuCommentList comments={post.comments} />
      </div>
    </div>
  )
}

export default CommuItemDetail
