import { useAtom } from "jotai";
import Head from "next/head";
import Image from "next/image";
import Token from "../atoms/Token";
import SignIn from "../components/Auth/SignIn";
import Body from "../components/Main/Body/Body";
import styles from "../styles/Home.module.css";

export default function Home() {
  const [token, setToken] = useAtom(Token);

  return token ? <Body /> : <SignIn />;
}
