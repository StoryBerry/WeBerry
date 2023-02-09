import React from "react";
import { useForm } from "react-hook-form";

const RegisteredFarm = (props) => {
  const farm = props.farm;
  const registers = props.registers;

  return farm ? (
    <div className="frame mt-4 ml-10 flex flex-col flex-nowrap overflow-x-scroll w-60">
      <div className="flex">
        <div className="font-bold mr-2">농장명:</div>
        <input
          {...registers.farmName}
          value={farm.farmName}
          readOnly="readonly"
        />
      </div>
      <div className="flex w-96">
        <div className="font-bold mr-2">지역:</div>
        <input
          {...registers.local}
          className="mr-4 w-12"
          value={farm.local}
          readOnly="readonly"
        />
        <div className="font-bold mr-2 w-12">시/군:</div>
        <input {...registers.city} value={farm.city} readOnly="readonly" />
      </div>
      <div className="flex w-96">
        <div className="font-bold mr-2">상세주소:</div>
        <input
          {...registers.address}
          value={farm.address}
          readOnly="readonly"
        />
      </div>
    </div>
  ) : (
    <div className="frame mt-4 ml-10 flex flex-col flex-nowrap overflow-x-scroll w-60">
      <div className="flex">
        <div className="font-bold mr-2">농장명:</div>
        <div>미정</div>
      </div>
      <div className="flex">
        <div className="font-bold mr-2">지역:</div>
        <div className="mr-4">미정</div>
        <div className="font-bold mr-2">시/군:</div>
        <div>미정</div>
      </div>
      <div className="flex w-96">
        <div className="font-bold mr-2">상세주소:</div>
        <div>미정</div>
      </div>
    </div>
  );
};

export default RegisteredFarm;
