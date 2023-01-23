import { comment } from "postcss";
import React from "react";
import CommuComment from "./CommuComment";

const CommuCommentList = (props) => {
  const comments = props.comments;
  console.log(comments);
  const commentMap = new Map();
  commentMap.set("main", []);
  comments.map((comment) =>
    comment.commentId === 0
      ? commentMap.get("main").push(comment)
      : commentMap.has(comment.commentId)
      ? commentMap.get(`${comment.commentId}`).push(comment)
      : commentMap.set(`${comment.commentId}`, [comment])
  );

  return comments.map((comment, idx) => (
    <CommuComment key={comment.id} comment={comment} />
  ));
};

export default CommuCommentList;
