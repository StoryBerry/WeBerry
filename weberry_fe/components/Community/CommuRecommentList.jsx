import React from "react";
import CommuRecomment from "./CommuRecomment";

const CommuRecommentList = (props) => {
  const recomments = props.recomments;

  return (
    <>
      <div className="ml-4 w-4/5">
        {recomments.map((recomment) => (
          <CommuRecomment key={recomment.id} recomment={recomment} />
        ))}
      </div>
    </>
  );
};

export default CommuRecommentList;
