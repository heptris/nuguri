/* 추상화된 컴포넌트들의 props 타입 */

import { ElementType, ReactNode } from "react";
import { CombineElementProps, OverridableProps } from "../types/utils";
import { AppBarProps as AppBarBaseProps } from "@mui/material/AppBar";
import { IconButtonProps } from "@mui/material/IconButton";

export type TextBaseProps = {
  typography?: "content";
};
export type TextProps<T extends ElementType> = OverridableProps<T, TextBaseProps>;

export type AppBarProps = AppBarBaseProps & { LeftChild: ReactNode; RightChild: ReactNode; heading?: string };
export type IconProps = IconButtonProps<
  ElementType,
  {
    mode: "GOBACK" | "NOTIFICATION" | "ADDCIRCLE" | "SEARCH" | "MYLOCATION" | "MENU";
  }
>;
