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
            <h2>실외습도 &rarr;</h2>
            <p>80</p>
          </a>
          <a
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
      </main> */}
    </div>
  )
}
