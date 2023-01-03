import React, { useState } from 'react'

const CommuComment = (props) => {
  const comment = props.comment
  const createdAt = Math.floor((Date.now() - Date.parse(comment.createdAt)) / 1000)

  const [toWrite, setToWrite] = useState(false);
  const [toSee, setToSee] = useState(false);

  return (
    <div className='comment-container flex flex-col'>
      <div className="comment-header flex space-x-4">
        <div className="commentor">{comment.user.nickName}</div>
        <div className="createdAt">{createdAt < 60 ? '방금전'
                                  : createdAt < 60 * 60 ? `${Math.floor(createdAt / 60)}분 전`
                                  : createdAt < 60 * 60 * 24? `${Math.floor(createdAt / 60 * 60)}시간 전` : `${Math.floor(createdAt / 60 * 60 * 24)}일 전`}</div>
      </div>
      <div className="comment-content">
        {comment.content}
      </div>
      <div className="comment-footer flex">
        {toWrite ? <div></div>
                 : <div className='px-2'>댓글작성</div>}
        {toSee ? <div></div>
                 : <div className='px-2'>댓글보기</div>}
      </div>
    </div>
  )
}

export default CommuComment