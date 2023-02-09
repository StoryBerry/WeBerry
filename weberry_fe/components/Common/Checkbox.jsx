import React from "react";

const Checkbox = (props) => {
  const farm = props.farm;
  const register = props.register;

  return (
    <div className="flex ">
      <input
        type="radio"
        name={farm.farmId}
        id={farm.farmId}
        value={farm.farmId}
        {...register}
        className="mr-4"
      />
      <div className="w-48 flex-nowrap overflow-x-scroll h-20 mt-2">
        <div className="flex w-96 justify-start">
          <label className="mr-1 text-sm font-bold w-12">농장명:</label>
          <div className="mr-1 text-sm w-16">{farm.farmName}</div>
        </div>
        <div className="flex w-96 justify-start">
          <label className="mr-1 text-sm font-bold w-8">지역:</label>
          <div className="mr-1 text-sm w-12">{farm.local}</div>
          <label className="mr-1 text-sm font-bold w-10">시/군:</label>
          <div className="mr-1 text-sm w-16 text-left">{farm.city}</div>
        </div>
        <div className="flex w-96 justify-start">
          <label className="mr-1 text-sm font-bold w-20 text-left">
            상세주소:
          </label>
          <div className="mr-1 text-sm w-96 text-left">{farm.address}</div>
        </div>
      </div>
    </div>
  );
};

export default Checkbox;
