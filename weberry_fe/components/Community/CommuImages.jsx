import Image from "next/image";
import React from "react";

const CommuImages = (props) => {
  const images = props.images;

  return images.length >= 4 ? (
    <div className="image-frame drop-shadow-xl rounded-xl grid grid-cols-2 w-350 h-150 border">
      {images.map((image, idx) =>
        idx > 3 ? (
          <></>
        ) : (
          <div className="image w-175 h-75 border">
            <Image
              className="object-contain"
              src={image.imageUrl}
              alt="image"
              width="175"
              height="75"
            />
          </div>
        )
      )}
    </div>
  ) : images.length === 3 ? (
    <div className="image-frame drop-shadow-xl rounded-xl grid grid-cols-2 w-350 h-150 border">
      {images.map((image, idx) =>
        idx === 0 ? (
          <div className="row-span-2 image w-175 border">
            <Image
              className="object-contain"
              src={image.imageUrl}
              alt="image"
              width="175"
              height="75"
            />
          </div>
        ) : (
          <div className="image w-175 h-75 border">
            <Image
              className="object-contain"
              src={image.imageUrl}
              alt="image"
              width="175"
              height="75"
            />
          </div>
        )
      )}
    </div>
  ) : images.length === 2 ? (
    <div className="image-frame drop-shadow-xl rounded-xl grid grid-cols-2 w-350 h-150 border">
      {images.map((image) => (
        <div className="image w-175 border">
          <Image
            className="object-contain"
            src={image.imageUrl}
            alt="image"
            width="175"
            height="150"
          />
        </div>
      ))}
    </div>
  ) : images.length === 1 ? (
    <div className="image-frame drop-shadow-xl rounded-xl grid grid-cols-2 w-350 h-150 border">
      <Image
        className="object-contain"
        src={images[0].imageUrl}
        alt="image"
        width="175"
        height="150"
      />
    </div>
  ) : (
    <></>
  );
};

export default CommuImages;
