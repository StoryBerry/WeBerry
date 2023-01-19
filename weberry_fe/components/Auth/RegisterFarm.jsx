import React, { useState } from "react";
import Input from "../Common/Input";

const RegisterFarm = (props) => {
  const register = props.register;
  const getValues = props.getValues;
  const setIsFarmInfo = props.setIsFarmInfo;

  const [isClicked, setIsClicked] = useState(false);
  const [farms, setFarms] = useState(null);

  const checkFarm = async () => {
    const farmName = getValues("farmInfo.farmName");

    await fetch(
      `http://localhost:8090/auth/sign-up/check/farm?farmName=${farmName}`
    )
      .then((response) => response.json())
      .then((data) => setFarms([...data]))
      .catch(() => setFarms([]));
  };

  return isClicked ? (
    <>
      <div
        className="bg-grey fixed z-20 w-full h-full -top-20"
        onClick={() => setIsClicked(!isClicked)}
      />
      <div className="fixed z-30 top-1/5 left-12 w-3/4 h-1/2 bg-white flex flex-col items-center">
        <form>
          <div className="relative mt-6">
            <Input
              name="farmName"
              koName="농장이름"
              placeholder="농장 이름을 입력해주세요."
              register={register("farmInfo.farmName")}
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
                <></>
              ) : (
                <>
                  <div className="mt-8 mb-10">등록된 농가가 없습니다.</div>
                  <Input
                    name="farmName"
                    koName="농장이름"
                    register={register("farmInfo.farmName")}
                    value={getValues("farmInfo.farmName")}
                  />
                  <Input
                    name="local"
                    koName="지역"
                    register={register("farmInfo.local")}
                  />
                  <Input
                    name="city"
                    koName="시/군"
                    register={register("farmInfo.city")}
                  />
                  <Input
                    name="address"
                    koName="상세주소"
                    register={register("farmInfo.address")}
                  />
                  <button
                    className="rounded-lg bg-green/60 font-bold text-xl p-2 mt-10"
                    onClick={() => {
                      setIsClicked(!isClicked);
                      setIsFarmInfo(true);
                    }}
                    type="button">
                    등록하기
                  </button>
                </>
              )
            ) : (
              <></>
            )}
          </div>
        </form>
      </div>
    </>
  ) : (
    <button
      type="button"
      className="bg-green/80 rounded-lg text-lg font-bold p-2"
      onClick={() => setIsClicked(!isClicked)}>
      농장등록
    </button>
  );
};

export default RegisterFarm;
