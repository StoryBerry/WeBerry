import React from "react";
import { useForm } from "react-hook-form";

const Select = (props) => {
  const type = props.type;
  const options = props.options;
  const koType = props.koType;
  const register = props.register;
  const setValue = props.setValue;
  const setLocal = props.setLocal;

  const changeHandler = (event) => {
    setLocal && setLocal(event.target.value);
  };

  return (
    <div className="flex flex-col">
      <div className="input-form flex justify-center w-80 mt-2">
        <label htmlFor={type} className="w-24 mr-8 text-end">
          {koType}
        </label>
        <select
          name={type}
          id={type}
          {...register}
          onChange={changeHandler}
          className="w-20 mr-24 placeholder:text-xs">
          <option value="none">선택하세요</option>
          {options &&
            options.map((option) => (
              <option key={option} value={option}>
                {option}
              </option>
            ))}
        </select>
      </div>
    </div>
  );
};

export default Select;
