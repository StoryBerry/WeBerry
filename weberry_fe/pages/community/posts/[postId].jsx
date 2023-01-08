import { useRouter } from "next/router";
import React from "react";
import CommuItemDetail from "../../../components/Community/CommuItemDetail";
import styles from "../../../styles/Home.module.css";

const citemdetail = () => {
  const router = useRouter();
  const { postId } = router.query;
  console.log("postid:", postId);

  return (
    <div className={styles.main}>
      <CommuItemDetail postId={postId} />
    </div>
  );
};

export default citemdetail;
