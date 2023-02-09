import React, { useEffect, useState } from "react";
import styles from "../../styles/Home.module.css";
import Image from "next/image";
import CommuCommentList from "./CommuCommentList";
import Token from "../../atoms/Token";
import { useAtom } from "jotai";
import { URLSearchParams } from "next/dist/compiled/@edge-runtime/primitives/url";
import ImageSlide from "../Common/ImageSlide";
import { baseUrl } from "../Constant/baseUrl";

const CommuItemDetail = (props) => {
  const [post, setPost] = useState(null);
  const [newComment, setNewComment] = useState(null);
  const [commented, setCommented] = useState(false);
  const [token, setToken] = useAtom(Token);

  const postId = props.postId;
  const images = [];
  post && post.images.map((image) => images.push(image.imageUrl));

  const commentHandler = (event) => setNewComment(event.target.value);
  const checkToken = async () => {
    let user = null;
    await fetch(`${baseUrl}/post/check/token`, {
      headers: { Authorization: token.token },
    })
      .then((response) => response.json())
      .then((data) => (user = JSON.parse(data["signIn"])))
      .catch((err) => console.error(err));

    return user;
  };
  const writeComment = async () => {
    const user = await checkToken();
    await fetch(`${baseUrl}/post/comment`, {
      method: "POST",
      headers: { "Content-Type": "application/json; charset=UTF-8" },
      body: JSON.stringify({
        content: newComment,
        userid: user.userid,
        postid: postId,
      }),
    })
      .then((response) => response.json)
      .then((data) => console.log(data))
      .catch((err) => console.error(err));
    setCommented(!commented);
    const input_ = document.getElementById("comment");
    input_.value = null;
  };

  useEffect(() => {
    fetch(`${baseUrl}/post/detail?` + new URLSearchParams({ postId: postId }))
      .then((response) => response.json())
      .then((data) => setPost(data))
      .catch((err) => console.error(err));
  }, [commented]);

  return (
    post && (
      <div className="w-full">
        <div className="profile-bar flex justify-start items-center mt-3 mb-4">
          <Image
            className=""
            src="/images/Commu-Img/2.png"
            alt="profile"
            width="50"
            height="50"
          />
          <h5 className="text-center font-semibold text-opacity-5 ">
            {post.user.farm.farmId}{" "}
          </h5>
          <h5 className="text-center ml-1"> @{post.user.nickName}</h5>
        </div>
        <ImageSlide images={images} />

        <h5 className="text-left text-xl mx-10 my-5">{post.content}</h5>
        <div className="write-comment flex justify-between mb-1">
          <input
            className="ml-10"
            type="text"
            placeholder="댓글달기..."
            id="comment"
            onChange={commentHandler}
          />
          <div
            className="button w-16 p-2 mr-5 bg-water text-black font-semibold rounded-lg"
            onClick={writeComment}>
            작성
          </div>
        </div>
        <div className={`${styles.card_button}`}>
          <CommuCommentList
            comments={post.comments}
            setCommented={setCommented}
          />
        </div>
      </div>
    )
  );
};

export default CommuItemDetail;
