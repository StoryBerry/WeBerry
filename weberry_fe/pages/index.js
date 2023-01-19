import Head from 'next/head'
import Image from 'next/image'
import Body from '../components/Main/Body/Body'
import styles from '../styles/Home.module.css'

export default function Home() {
  return (

    <div>
      <div>
        <Body />
      </div>
      {/* <main className>
        <div className={styles.grid}>
          <a className={styles.card}>
            <h2>실내습도 &rarr;</h2>
            <p>60</p>
          </a>
          <a className={styles.card}>
      </main> */}
    </div>
  )
}
