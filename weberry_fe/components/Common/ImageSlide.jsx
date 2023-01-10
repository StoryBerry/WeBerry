import Image from "next/image";
import React, { useState } from "react";

const ImageSlide = (props) => {
  const [imageno, setImageno] = useState(0);
  const images = props.images;

  const prevImageno = () => {
    imageno - 1 < 0 ? setImageno(images.length - 1) : setImageno(imageno - 1);
  };

  const nextImageno = () => {
    imageno + 1 === images.length ? setImageno(0) : setImageno(imageno + 1);
  };

  return (
    images.length > 0 && (
      <div className="image-frame drop-shadow-xl rounded-xl relative w-300 h-200">
        <div
          className="slider absolute top-20 left-0 font-black text-2xl"
          onClick={prevImageno}>
          &#60;
        </div>
        <div
          className="slider absolute top-20 right-0 font-black text-2xl"
          onClick={nextImageno}>
          &#62;
        </div>
        <Image src={images[imageno]} width="300" height="200" alt="image" />
      </div>
    )
  );
};

export default ImageSlide;
