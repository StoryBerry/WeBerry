import React, { useEffect, useState } from 'react'
import Body from '../../components/Main/Body/Body'
import styles from '../../styles/Home.module.css'
import Cmain from '../../components/Commu/Cmain'
import { fromJSON } from 'postcss'

const cindex = () => {
  const [posts, setPosts] = useState([])
  useEffect(() => {
    const dataList = [];
    const posts = fetch('http://localhost:8090/post')
                    .then(response => response.json())
                    .then(datas => setPosts(datas))
                    .catch(err => console.error(err))

    console.log('posts: ' + posts);
    console.log('post[0]: ' + posts[0]);
    console.log('post[0]["id"]: ' + posts[0].id);
  }, [])

  return (
    <>
  <div className={styles.container}>
       <div>
        < Cmain />
       </div>
      
    </div>
    </>
  )
}

export default cindex