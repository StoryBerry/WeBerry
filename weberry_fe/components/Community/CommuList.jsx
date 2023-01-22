import React from "react";
import CommuItem from "./CommuItem";
import Link from "next/link";

const CommuList = (props) => {
  const posts = props.posts;

  return (
    <>
      {posts.map((post) => (
        <Link href={{ pathname: `/community/posts/${post.id}` }}>
          <CommuItem post={post} />
        </Link>
      ))}
    </>
  );
};

export default CommuList;
