import { ButtonHTMLAttributes, forwardRef } from "react";

import { css } from "@emotion/react";

export const Button = forwardRef<HTMLButtonElement, IButtonProps>((props, ref) => {
  const { size = "md", color = "blue", content, disabled, fullWidth, ...restProps } = props;
  return (
    <>
      <button color={color} ref={ref} css={css``} type={"button"} disabled={disabled} {...restProps}>
        {content}
      </button>
      <h1>이거?</h1>
    </>
  );
});

interface IButtonProps extends ButtonHTMLAttributes<HTMLButtonElement> {
  size?: "xs" | "sm" | "md" | "lg" | "xl";
  color?: string;
  content?: string;
  fullWidth?: boolean;
}
