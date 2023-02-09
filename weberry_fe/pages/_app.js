import "../styles/globals.css";
import Header from "../components/Layout/Header";
import Footer from "../components/Layout/Footer";

function MyApp({ Component, pageProps }) {
  return (
    <>
      <Header />
      <div className="font-extralight p-2 mb-4">
        <Component {...pageProps} />
      </div>
      <Footer />
    </>
  );
}

/**mx-auto max-w-5xl px-4 sm:px-6 flex  items-center justify-between  */
export default MyApp;
