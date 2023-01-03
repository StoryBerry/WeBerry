import { useRouter } from 'next/router'
import React from 'react'
import CommuItemDetail from '../../../components/Community/CommuItemDetail'
import styles from '../../../styles/Home.module.css'


const citemdetail = () => {
  const router = useRouter();
  const {postId, postObj} = router.query;
  const post = JSON.parse(postObj);

  return (
    <div className={styles.main}>
      <CommuItemDetail post={post}/>
    </div>
    )
}

export default citemdetail