import React from "react";

const Checkbox = (props) => {
  const farm = props.farm;
  const register = props.register;

  return (
    <div className="flex ">
      <input
        type="checkbox"
        name={farm.farmId}
        id={farm.farmId}
        value={farm.farmId}
        {...register}
        className="mr-4"
      />
      <div className="w-48 flex-nowrap overflow-x-scroll">
        <div className="flex flex-nowrap mb-4">
          <label className="mr-1 text-sm font-bold w-24 h-4">농장명:</label>
          <input
            className="mr-2 text-sm w-16"
            value={farm.farmName}
            readOnly="readonly"
            {...register("farmInfo.farmName")}
          />
          <label className="mr-1 text-sm font-bold w-24 h-4">지역:</label>
          <input
            className="mr-2 text-sm w-16"
            value={farm.local}
            readOnly="readonly"
            {...register("farmInfo.local")}
          />
          <label className="mr-1 text-sm font-bold w-24 h-4">시/군:</label>
          <input
            className="mr-2 text-sm w-16"
            value={farm.city}
            readOnly="readonly"
            {...register("farmInfo.city")}
          />
        </div>
        <div className="flex">
          <label className="mr-1 text-sm font-bold w-20">상세주소:</label>
          <input
            className="mr-2 text-sm"
            value={farm.address}
            readOnly="readonly"
            {...register("farmInfo.address")}
          />
        </div>
      </div>
    </div>
  );
};

export default Checkbox;
