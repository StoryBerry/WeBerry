import React, { useEffect, useRef, useState } from "react";
import ErrorMessage from "../Error/ErrorMessage";

const CommuWrite = (props) => {
  const setToWritePost = props.setToWritePost;
  const toWritePost = props.toWritePost;
  const token = props.token;

  const ref = useRef(null);
  const [content, setContent] = useState("");
  const [images, setImage] = useState([]);
  const [signIn, setSignIn] = useState({});
  const [imagePreview, setImagePreview] = useState("");
  const clickHandler = () => setToWritePost(!toWritePost);
  const contentHandler = (event) => setContent(event.target.value);
  const imageHandler = (event) => {
    setImage([...event.target.files]);
  };
  const buttonHandler = (event) => {
    event.target.innerText === "작성" ? writePost() : setToWritePost(!toWritePost);
  };
  const writePost = () => {
    console.log(content, signIn);
    const formData = new FormData();
    const body = { content: content, user: signIn };
    formData.set("content", content);
    formData.set("userid", signIn.userid);
    formData.set("imageFiles", images[0]);
    fetch("http://localhost:8090/post/write", {
      method: "POST",
      body: formData,
    });
    clickHandler();
  };

  useEffect(() => {
    fetch("http://localhost:8090/post/check/token", {
      headers: { Authorization: token.token },
    })
      .then((response) => response.json())
      .then((data) => setSignIn({ ...JSON.parse(data["signIn"]) }))
      .catch((err) => console.error(err));
  }, []);
  useEffect(() => {
    if (images && images.length > 0) {
      const file = images[0];
      console.log(URL.createObjectURL(file));
      setImagePreview(URL.createObjectURL(file));
    }
  }, [images]);
  return (
    <>
      <div className="fixed bg-grey/80 z-25 w-full h-full top-10 " onClick={clickHandler} />
      {signIn["userid"] ? (
        <div className="absolute bg-white z-30 top-15 w-4/5 left-9 content-center">
          <textarea ref={ref} onChange={contentHandler} className="my-8 p-2 w-4/5" cols="30" rows="10" placeholder="무엇을 작성하시겠습니까?" />
          <input ref={ref} onChange={imageHandler} className="files my-2 w-4/5" type="file" name="files" multiple="multiple" accept="image/png, image/jpg" />
          {imagePreview && <img src={imagePreview} />}
          <div className="buttons flex w-4/5 justify-around mx-8 my-4">
            <div
              className="button w-16 p-2 bg-water text-black font-semibold rounded-lg shadow-md"
              onClick={buttonHandler}
            >
              작성
            </div>
            <div
              className="button w-16 p-2 bg-grey text-black font-semibold rounded-lg shadow-md"
              onClick={buttonHandler}
            >
              취소
            </div>
          </div>
        </div>
      ) : (
        <ErrorMessage err={signIn["error"]} />
      )}
    </>
  );
};

export default CommuWrite;
