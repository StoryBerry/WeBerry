import React, { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import RegisterFarm from "../../../components/Auth/RegisterFarm";
import Input from "../../../components/Common/Input";

const index = () => {
  const {
    register,
    unregister,
    handleSubmit,
    getValues,
    setValue,
    setError,
    formState: { errors },
  } = useForm();
  const [okStatus, setOkStatus] = useState(null);
  const [isFarmInfo, setIsFarmInfo] = useState(false);
  const onSubmit = async (data) => {
    console.log(getValues("userInfo.password"));
    console.log(getValues("userInfo.passwordCheck"));
    if (
      getValues("userInfo.password") !== getValues("userInfo.passwordCheck")
    ) {
      setError("userInfo.passwordCheck", {
        type: "custom",
        message: "비밀번호가 같지 않습니다.",
      });
      setValue("userInfo.passwordCheck", "");
    } else {
      await fetch();
    }
  };
  const checkUserId = async () => {
    const userid = getValues("userInfo.userid");

    userid && errors.userInfo && unregister("userInfo.userid");
    userid && okStatus && setOkStatus(null);
    userid &&
      (await fetch(
        `http://localhost:8090/auth/sign-up/check/user?userId=${userid}`
      )
        .then((response) => response.json())
        .then((data) =>
          data === true
            ? setOkStatus("사용가능한 아이디입니다.")
            : setError("userInfo.userid", {
                type: "custom",
                message: "이미 사용중인 아이디입니다.",
              })
        ));
  };

  return (
    <form
      className="frame mt-40 p-4 flex flex-col items-center"
      onSubmit={handleSubmit(onSubmit)}>
      <div className="id flex relative">
        <Input
          name="userid"
          placeholder="아이디를 입력해주세요."
          koName="아이디"
          isButton={true}
          register={register("userInfo.userid")}
          errors={errors.userInfo?.userid}
          okStatus={okStatus}
        />
        <button
          className="absolute -right-2 -top-1 font-bold w-20 rounded-lg bg-berry/60 text-white p-1"
          type="button"
          onClick={checkUserId}>
          중복확인
        </button>
      </div>
      <Input
        name="password"
        placeholder="비밀번호를 입력해주세요."
        koName="비밀번호"
        type="password"
        register={register("userInfo.password")}
      />
      <Input
        name="password"
        placeholder="비밀번호를 입력해주세요."
        koName="비밀번호확인"
        type="password"
        register={register("userInfo.passwordCheck")}
        errors={errors.userInfo?.passwordCheck}
      />
      <Input
        name="name"
        placeholder="이름을 입력해주세요."
        koName="이름"
        register={register("userInfo.nickName")}
      />
      <Input
        name="nickName"
        placeholder="닉네임을 입력해주세요."
        koName="닉네임"
        register={register("userInfo.nickName")}
      />
      {isFarmInfo ? (
        <>ok</>
      ) : (
        <RegisterFarm
          register={register}
          getValues={getValues}
          setIsFarmInfo={setIsFarmInfo}
        />
      )}
      <button
        type="submit"
        className="bg-berry/60 text-white text-xl font-bold w-28 rounded-xl p-2">
        회원가입
      </button>
    </form>
  );
};

export default index;
