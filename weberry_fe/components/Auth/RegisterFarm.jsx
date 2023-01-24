import React, { useState } from "react";
import address from "../../public/Address";
import Checkbox from "../Common/Checkbox";
import Input from "../Common/Input";
import Select from "../Common/Select";
import { baseUrl } from "../Constant/baseUrl";

const RegisterFarm = (props) => {
  const register = props.register;
  const getValues = props.getValues;
  const setIsFarmInfo = props.setIsFarmInfo;
  const setValue = props.setValue;
  const farms = props.farms;
  const setFarms = props.setFarms;

  const [isClicked, setIsClicked] = useState(false);
  const [local, setLocal] = useState(null);

  const checkFarm = async (event) => {
    const farmName = getValues("farmInfo.farmName");

    await fetch(`${baseUrl}/auth/sign-up/check/farm?farmName=${farmName}`)
      .then((response) => response.json())
      .then((data) => setFarms([...data]))
      .catch(() => setFarms([]));
  };
  const registerFarm = async () => {
    let farm = getValues("farmInfo");
    !getValues("farmInfo.farmId") &&
      (await fetch(`${baseUrl}/auth/sign-up/create/farm`, {
        method: "POST",
        headers: { "Content-type": "application/json; charset=UTF-8" },
        body: JSON.stringify(farm),
      }));
    setIsClicked(!isClicked);
    setIsFarmInfo(true);
  };

  return isClicked ? (
    <>
      <div
        className="bg-grey fixed z-20 w-full h-full -top-20"
        onClick={() => setIsClicked(!isClicked)}
      />
      <div className="fixed z-30 top-1/5 left-12 w-3/4 h-1/2 bg-white flex flex-col items-center">
        <div className="relative mt-6">
          <Input
            name="farmName"
            koName="농장이름"
            placeholder="농장 이름을 입력해주세요."
            onChange={(event) =>
              setValue("farmInfo.farmName", event.target.value)
            }
          />
          <button
            type="button"
            className="absolute right-3 top-0 bg-green/60 w-12 rounded-lg font-bold p-2"
            onClick={checkFarm}>
            검색
          </button>
        </div>
        <div className="flex flex-col items-center justify-center">
          {farms ? (
            farms.length > 0 ? (
              <>
                <div className="checkbox h-44 overflow-y-scroll scroll-p-20">
                  {farms.map((farm) => (
                    <Checkbox
                      key={farm.farmId}
                      farm={farm}
                      register={register("farmInfo.farmId", {
                        required: true,
                      })}
                    />
                  ))}
                </div>
                <Input
                  name="farmName"
                  koName="농장이름"
                  register={register("farmInfo.farmName")}
                  value={getValues("farmInfo.farmName")}
                />
                <Select
                  type="local"
                  koType="지역"
                  register={register("farmInfo.local")}
                  setValue={setValue}
                  options={address.local}
                  setLocal={setLocal}
                />
                <Select
                  type="city"
                  koType="시/군"
                  register={register("farmInfo.city")}
                  setValue={setValue}
                  options={local && address.city[local]}
                />
                <Input
                  name="address"
                  koName="상세주소"
                  register={register("farmInfo.address")}
                />
                <button
                  className="rounded-lg bg-green/60 font-bold text-xl p-2 mt-10"
                  onClick={registerFarm}
                  type="button">
                  등록하기
                </button>
              </>
            ) : (
              <>
                <div className="mt-8 mb-10">등록된 농가가 없습니다.</div>
                <Input
                  name="farmName"
                  koName="농장이름"
                  register={register("farmInfo.farmName")}
                  value={getValues("farmInfo.farmName")}
                />
                <Select
                  type="local"
                  koType="지역"
                  register={register("farmInfo.local")}
                  setValue={setValue}
                  options={address.local}
                  setLocal={setLocal}
                />
                <Select
                  type="city"
                  koType="시/군"
                  register={register("farmInfo.city")}
                  setValue={setValue}
                  options={local && address.city[local]}
                />
                <Input
                  name="address"
                  koName="상세주소"
                  register={register("farmInfo.address")}
                />
                <button
                  className="rounded-lg bg-green/60 font-bold text-xl p-2 mt-10"
                  onClick={registerFarm}
                  type="button">
                  등록하기
                </button>
              </>
            )
          ) : (
            <></>
          )}
        </div>
      </div>
    </>
  ) : (
    <button
      type="button"
      className="bg-green/80 rounded-lg text-lg font-bold p-2 mt-5"
      onClick={() => setIsClicked(!isClicked)}>
      농장등록
    </button>
  );
};

export default RegisterFarm;
