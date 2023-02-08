import React from "react";

const CommuRecomment = (props) => {
  const recomment = props.recomment;
  const createdAt = Math.floor(
    (Date.now() - Date.parse(recomment.createdAt)) / 1000
  );

  return (
    <>
      <div className="comment-header flex justify-between w-full">
        <div className="commentor ml-4 text-base">
          {recomment.user.nickName}
        </div>
        <div className="createdAt mr-4 text-base">
          {createdAt < 60
            ? "방금전"
            : createdAt < 60 * 60
            ? `${Math.floor(createdAt / 60)}분 전`
            : createdAt < 60 * 60 * 24
            ? `${Math.floor((createdAt / 60) * 60)}시간 전`
            : `${Math.floor((createdAt / 60) * 60 * 24)}일 전`}
        </div>
      </div>
      <div className="comment-content text-left ml-6 text-base">
        {recomment.content}
      </div>
    </>
  );
};

export default CommuRecomment;
