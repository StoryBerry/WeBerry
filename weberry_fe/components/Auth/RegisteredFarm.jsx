import React from "react";

const RegisteredFarm = (props) => {
  const getValues = props.getValues;
  const farms = props.farms;
  const farm = getValues("farmInfo");
  const farmId = getValues("farmInfo.farmId");

  return farmId ? (
    farms.map(
      (farm) =>
        farm.farmId === farmId && (
          <div
            key={farm.farmId}
            className="frame mt-4 ml-10 flex flex-col flex-nowrap overflow-x-scroll w-60">
            <div className="flex">
              <div className="font-bold mr-2">농장명:</div>
              <div>{farm.farmName}</div>
            </div>
            <div className="flex">
              <div className="font-bold mr-2">지역:</div>
              <div className="mr-4">{farm.local}</div>
              <div className="font-bold mr-2">시/군:</div>
              <div>{farm.city}</div>
            </div>
            <div className="flex w-96">
              <div className="font-bold mr-2">상세주소:</div>
              <div>{farm.address}</div>
            </div>
          </div>
        )
    )
  ) : (
    <div className="frame mt-4 ml-10 flex flex-col flex-nowrap overflow-x-scroll w-60">
      <div className="flex">
        <div className="font-bold mr-2">농장명:</div>
        <div>{farm.farmName}</div>
      </div>
      <div className="flex">
        <div className="font-bold mr-2">지역:</div>
        <div className="mr-4">{farm.local}</div>
        <div className="font-bold mr-2">시/군:</div>
        <div>{farm.city}</div>
      </div>
      <div className="flex w-96">
        <div className="font-bold mr-2">상세주소:</div>
        <div>{farm.address}</div>
      </div>
    </div>
  );
};

export default RegisteredFarm;
