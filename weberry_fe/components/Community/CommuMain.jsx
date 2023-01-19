import React, {useState} from 'react'
import CommuList from './CommuList'



const CommuMain = (props) => {
  return (
    <div className= 'text-sm my-20 justify-center items-center'>
      <CommuList posts={props.posts}/>
    </div>
  )
}

export default CommuMain