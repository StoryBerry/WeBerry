import React from 'react'
import styles from '../../styles/Home.module.css'
import Image from 'next/image'
import CommuCommentList from './CommuCommentList';

const CommuItemDetail = (props) => {
  const post = props.post;

  return (

    <div className={styles.card_board}>
      <div className='next-visit'>
        <Image className="float-left" src='/images/Commu-Img/2.png' width='16' height='16' />
        <h5 className={styles.card_font} >{post.user.farm.farmId} {post.user.nickName}</h5>
      </div>

      <h5 className='text-xs' >
        {post.content}
      </h5>

      <img className='' src='/images/strawberry.jpeg' width='350' height='194' />
      
      <div className={styles.card_button}>
        <CommuCommentList comments={post.comments} />
      </div>
    </div>
  )
}

export default CommuItemDetail
