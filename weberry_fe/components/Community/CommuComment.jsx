import { useAtom } from "jotai";
import React, { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import Token from "../../atoms/Token";
import { baseUrl } from "../Constant/baseUrl";
import CommuRecommentList from "./CommuRecommentList";

const CommuComment = (props) => {
  const comment = props.comment;
  const recomments = props.recomments;
  const setCommented = props.setCommented;
  const createdAt = Math.floor(
    (Date.now() - Date.parse(comment.createdAt)) / 1000
  );
  console.log(comment);
  console.log(recomments);

  const { register, handleSubmit, getValues } = useForm();
  const [toWrite, setToWrite] = useState(false);
  const [toSee, setToSee] = useState(false);
  const [signIn, setSignIn] = useState(null);
  const [token, setToken] = useAtom(Token);

  const checkToken = async () => {
    let user = null;
    await fetch(`${baseUrl}/post/check/token`, {
      headers: { Authorization: token.token },
    })
      .then((response) => response.json())
      .then((data) => (user = JSON.parse(data["signIn"]).userid))
      .catch((err) => console.error(err));

    return user;
  };
  const onSubmit = async (data) => {
    const userid = await checkToken();
    const body = {
      content: getValues("content"),
      userid: userid,
      postid: comment.post.id,
      commentid: comment.id,
    };
    await fetch(`${baseUrl}/post/recomment`, {
      method: "POST",
      headers: { "Content-type": "application/json; charset=UTF-8" },
      body: JSON.stringify(body),
    });
    setToWrite(!toWrite);
    setCommented(true);
  };

  return (
    <div className="comment-container flex flex-col w-full mt-2">
      <div className="comment-header flex justify-between">
        <div className="commentor ml-4 text-base">{comment.user.nickName}</div>
        <div className="createdAt mr-4 text-base">
          {createdAt < 60
            ? "방금전"
            : createdAt < 60 * 60
            ? `${Math.floor(createdAt / 60)}분 전`
            : createdAt < 60 * 60 * 24
            ? `${Math.floor(createdAt / 60 / 60)}시간 전`
            : `${Math.floor(createdAt / 60 / 60 / 24)}일 전`}
        </div>
      </div>
      <div className="comment-content text-left ml-6 text-base">
        {comment.content}
      </div>
      {!toWrite && !toSee ? (
        <div className="comment-footer flex justify-around my-1">
          <div className="text-sm" onClick={() => setToWrite(!toWrite)}>
            댓글작성
          </div>
          <div
            className="text-sm"
            onClick={() => (recomments ? setToSee(!toSee) : <></>)}>
            댓글보기
          </div>
        </div>
      ) : toWrite && !toSee ? (
        <div className="comment-footer flex flex-col items-start ml-2">
          <form
            onSubmit={handleSubmit(onSubmit)}
            className="w-full h-6 flex justify-start my-1">
            <input
              type="text"
              placeholder="댓글달기..."
              className="w-3/5 text-base mx-4"
              {...register("content")}
            />
            <button type="submit" className="text-sm">
              작성
            </button>
          </form>
          <div
            className="text-sm ml-4"
            onClick={() => (recomments ? setToSee(!toSee) : <></>)}>
            댓글보기
          </div>
        </div>
      ) : !toWrite && toSee ? (
        <div className="comment-footer flex flex-col items-start">
          <div className="comment-footer flex justify-around w-full my-1">
            <div className="text-sm" onClick={() => setToWrite(!toWrite)}>
              댓글작성
            </div>
            <div className="text-sm" onClick={() => setToSee(!toSee)}>
              댓글닫기
            </div>
          </div>
          <CommuRecommentList recomments={recomments} />
        </div>
      ) : (
        <div className="comment-footer flex flex-col items-start ml-4">
          <form
            onSubmit={handleSubmit(onSubmit)}
            className="w-full h-6 flex justify-start my-1">
            <input
              type="text"
              placeholder="댓글달기..."
              className="w-3/5 text-base mx-4"
              {...register("content")}
            />
            <button type="submit" className="text-sm">
              작성
            </button>
          </form>
          <div className="text-sm ml-4 my-1" onClick={() => setToSee(!toSee)}>
            댓글닫기
          </div>
          <CommuRecommentList recomments={recomments} />
        </div>
      )}
      <div className="w-4/5 border border-dashed border-x-indigo-500 ml-8 mt-2" />
    </div>
  );
};

export default CommuComment;
