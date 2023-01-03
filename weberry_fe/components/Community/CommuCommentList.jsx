import React from 'react'
import CommuComment from './CommuComment'

const CommuCommentList = (props) => {
  const comments = props.comments

  return (
    comments.map(comment => <CommuComment comment={comment} />)
  )
}

export default CommuCommentList