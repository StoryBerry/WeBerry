import React from 'react'
import styles from '../../styles/Home.module.css'
import Image from 'next/image'
// import strawberry form './strawberry.jpeg'


const CommuItemDetail = () => {

  return (

    <div className={styles.card_board}>

      {/* <img layout='responsive' src=''/> */}
      <div className='next-visit'>
        <Image className="float-left" src='/images/Commu-Img/2.png' width='16' height='16' />
        <h5 className={styles.card_font} >김다현</h5>
      </div>

      <h5 className='text-xs' >
        첫 딸기 재배 성공했어요!
        딸기 재배 교육 받았다고 해도ㅠ 정보가 머릿속에 뒤죽박죽으로 엉켜있어서 잘 키울수
        있을까 고민했는데, 위베리 어플이 딱딱 필요한 정보를 제때 알려줘서 헤매는 일없이
        성공적으로 재배했네요! 다들 파이팅!
      </h5>

      <img className='' src='/images/strawberry.jpeg' width='350' height='194' />
      {/* <Image src= {strawberry} width={259} height={194} alt='사진'/> */}

      <div className={styles.card_button}>
        <div className='text-'> 댓글 8 </div>
      </div>
    </div>
  )
}

export default CommuItemDetail