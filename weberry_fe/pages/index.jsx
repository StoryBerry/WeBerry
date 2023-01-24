import { useAtom } from "jotai";
import Token from "../atoms/Token";
import SignIn from "../components/Auth/SignIn";
import Body from "../components/main/Body/Bodys";

export default function Home() {
  const [token, setToken] = useAtom(Token);

  return token ? <Body /> : <SignIn />;
}
