import React, { useEffect, useState } from 'react'
import styles from '../../styles/Home.module.css'
import CommuMain from '../../components/Community/CommuMain'
import CommuWrite from '../../components/Community/CommuWrite';
import { useAtom } from 'jotai';
import Token from '../../atoms/Token';

const index = () => {
  const [posts, setPosts] = useState([]);
  const [toWritePost, setToWritePost] = useState(false);
  const [token, setToken] = useAtom(Token);

  const clickHandler = () => setToWritePost(!toWritePost);

  useEffect(() => {
    fetch('http://localhost:8090/post')
        .then(response => response.json())
        .then(datas => {
          console.log(datas);
          setPosts(datas)
        })
        .catch(err => console.error(err))
  }, [toWritePost])

  return (
    <>
    {toWritePost && <CommuWrite setToWritePost={setToWritePost} toWritePost={toWritePost} token={token}/>}
    <div className={styles.container}>
      <div className='sticky left-3/4 top-20 py-1 px-1 bg-grey text-berry font-semibold rounded-lg shadow-md w-20'
           onClick={clickHandler}>글쓰기</div>
      {posts && <CommuMain posts={posts}/>}
    </div>
    </>
  )
}

export default index