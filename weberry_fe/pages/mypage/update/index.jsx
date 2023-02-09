import { useAtom } from "jotai";
import { useRouter } from "next/router";
import React, { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import Token from "../../../atoms/Token";
import RegisteredFarm from "../../../components/Auth/RegisteredFarm";
import RegisterFarm from "../../../components/Auth/RegisterFarm";
import Input from "../../../components/Common/Input";
import { baseUrl } from "../../../components/Constant/baseUrl";

const Index = () => {
  const { register, handleSubmit, getValues, setValue, unregister } = useForm();

  const [updatedFarmInfo, setUpdatedFarmInfo] = useState(null);
  const [farms, setFarms] = useState(null);
  const [userId, setUserId] = useState(null);
  const [name, setName] = useState(null);
  const [nickName, setNickName] = useState(null);
  const [farm, setFarm] = useState({});
  const [token, setToken] = useAtom(Token);
  const router = useRouter();

  const registers = {
    farmName: register("farmInfo.farmName"),
    local: register("farmInfo.local"),
    city: register("farmInfo.city"),
    address: register("farmInfo.address"),
  };
  const onSubmit = async (data) => {
    console.log(`data: ${JSON.stringify(data)}`);
    await fetch(`${baseUrl}/auth/update`, {
      method: "POST",
      headers: { "Content-type": "application/json; charset=UTF-8" },
      body: JSON.stringify(data),
    })
      .then((response) => response.json())
      .then((data) => setToken(data))
      .catch((err) => console.error(err));
    router.push("/mypage");
  };
  useEffect(() => {
    !updatedFarmInfo &&
      fetch(`${baseUrl}/auth/check/token`, {
        headers: { Authorization: token.token },
      })
        .then((response) => response.json())
        .then((data) => {
          const signIn = JSON.parse(data["signIn"]);
          console.log(signIn);
          setUserId(signIn.userid);
          setName(signIn.name);
          setNickName(signIn.nickName);
          setFarm({ ...signIn.farm });
        })
        .catch((err) => console.error(err));
  }, [updatedFarmInfo]);

  return (
    userId && (
      <form
        className="frame mt-40 p-4 flex flex-col items-center"
        onSubmit={handleSubmit(onSubmit)}>
        <Input
          name="userid"
          placeholder="아이디를 입력해주세요."
          koName="아이디"
          register={register("userInfo.userid")}
          readOnly="readonly"
          value={userId}
        />
        <Input
          name="name"
          placeholder="이름을 입력해주세요."
          koName="이름"
          register={register("userInfo.name")}
          value={name}
          onChange={(event) => setName(event.target.content)}
        />
        <Input
          name="nickName"
          placeholder="닉네임을 입력해주세요."
          koName="닉네임"
          register={register("userInfo.nickName")}
          value={nickName}
          onChange={(event) => setNickName(event.target.content)}
        />
        <RegisteredFarm
          farm={updatedFarmInfo ? updatedFarmInfo : farm}
          registers={registers}
        />
        <RegisterFarm
          register={register}
          getValues={getValues}
          setUpdatedFarmInfo={setUpdatedFarmInfo}
          setValue={setValue}
          handleSubmit={handleSubmit}
          farms={farms}
          setFarms={setFarms}
          unregister={unregister}
        />
        <button
          type="submit"
          className="bg-berry/60 text-white text-xl font-bold w-32 rounded-xl p-2 mt-5">
          프로필 수정
        </button>
      </form>
    )
  );
};

export default Index;
