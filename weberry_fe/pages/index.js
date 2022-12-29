import Head from 'next/head'
import Image from 'next/image'
import Body from '../components/Main/Body/Body'
import styles from '../styles/Home.module.css'

export default function Home() {
  return (

    <div className={styles.container}>
       <div>
      <Body />
      </div>
      <main className>
        
        <p className={styles.description}>
          데일리리포트내용{' '}
        </p>

        <div className={styles.grid}>
          <a href="https://nextjs.org/docs" className={styles.card}>
            <h2>실내습도 &rarr;</h2>
            <p>60</p>
          </a>

          <a href="https://nextjs.org/learn" className={styles.card}>
            <h2>실외습도 &rarr;</h2>
            <p>80</p>
          </a>
          <a
            href="https://github.com/vercel/next.js/tree/canary/examples"
            className={styles.card}
          >
            <h2>실내온도 &rarr;</h2>
            <p>40.8</p>
          </a>

          <a
            href="https://vercel.com/new?utm_source=create-next-app&utm_medium=default-template&utm_campaign=create-next-app"
            className={styles.card}
          >
            <h2>실외 온도 &rarr;</h2>
            <p>
              36.5
            </p>
          </a>
        </div>
      </main>
    </div>
  )
}
