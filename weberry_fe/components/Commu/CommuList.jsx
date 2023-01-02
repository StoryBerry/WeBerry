import React from 'react'
import CommuItem from './CommuItem'
import Link from 'next/link'
const CommuList = () => {
  return (
    <>
  <Link href ='/commu/citem'>
    <CommuItem />
   </Link>
   <CommuItem />
   <CommuItem />
   <CommuItem />
   <CommuItem />
   </>
  )
}

export default CommuList