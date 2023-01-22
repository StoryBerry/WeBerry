import React from "react";

const Input = ({
  name,
  koName,
  type = "text",
  placeholder,
  register,
  errors,
  okStatus,
  value,
  onChange,
}) => {
  return (
    <div className="flex flex-col">
      <div className="input-form flex justify-center w-80 mt-2">
        <label htmlFor={name} className="w-24 text-end">
          {koName}
        </label>
        <input
          type={type}
          placeholder={placeholder}
          className="ml-4 w-48 placeholder:text-xs"
          {...register}
          value={value}
          onChange={onChange}
        />
      </div>
      {errors && (
        <div className="mr-4 text-xs text-berry font-bold ">
          {errors.message}
        </div>
      )}
      {okStatus && (
        <div className="mr-4 text-xs text-green font-bold ">{okStatus}</div>
      )}
    </div>
  );
};

export default Input;
