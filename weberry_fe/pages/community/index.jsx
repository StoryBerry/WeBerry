import React, { useEffect, useState } from "react";
import styles from "../../styles/Home.module.css";
import CommuMain from "../../components/Community/CommuMain";
import CommuWrite from "../../components/Community/CommuWrite";
import { useAtom } from "jotai";
import Token from "../../atoms/Token";
import Image from "next/image";
import { baseUrl } from "../../components/Constant/baseUrl";

const Index = () => {
  const [posts, setPosts] = useState([]);
  const [toWritePost, setToWritePost] = useState(false);
  const [token, setToken] = useAtom(Token);

  const clickHandler = () => setToWritePost(!toWritePost);

  useEffect(() => {
    fetch(`${baseUrl}/post`)
      .then((response) => response.json())
      .then((datas) => setPosts(datas))
      .catch((err) => console.error(err));
  }, [toWritePost]);

  return (
    <>
      {toWritePost && (
        <CommuWrite
          setToWritePost={setToWritePost}
          toWritePost={toWritePost}
          token={token}
        />
      )}

      <div className="items-center px-8">
        {posts && <CommuMain posts={posts} />}
      </div>
      <Image
        className="fixed bottom-16 right-5 z-30 ali"
        src="/images/Commu-Img/add-button.png"
        width="80"
        height="80"
        onClick={clickHandler}
        alt="add-button"
      />
    </>
  );
};

export default Index;
