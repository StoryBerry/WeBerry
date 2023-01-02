import React, { useEffect, useState } from 'react'
import styles from '../../styles/Home.module.css'
import CommuItem from '../../components/Commu/CommuItem'

const cindex = () => {
  const [posts, setPosts] = useState([])
  useEffect(() => {
    const dataList = [];
    const posts = fetch('http://localhost:8090/post')
                    .then(response => response.json())
                    .then(datas => {
                      console.log(datas);
                      setPosts(datas)
                    })
                    .catch(err => console.error(err))
  }, [])

  return posts? (
    <div className={styles.container}>
      {posts.map(post => {
        return (< CommuItem post={post}/>)
      })}
    </div>
  ) : null;
}

export default cindex