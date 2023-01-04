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
        .then(datas => setPosts(datas))
        .catch(err => console.error(err))
  }, [toWritePost])
  console.log(token);

  return (
    <>
    {toWritePost && <CommuWrite setToWritePost={setToWritePost} toWritePost={toWritePost} token={token}/>}
    <div className={styles.container}>
      <div className='sticky left-3/4 top-20  px-2 bg-light_pink hover:bg-gray-200 text-black text-3xl font-extrabold text-center rounded-full h-10 w-10 inline-flex items-center'
           onClick={clickHandler}>+</div>
      {posts && <CommuMain posts={posts}/>}
    </div>
    </>
  )
}

export default index