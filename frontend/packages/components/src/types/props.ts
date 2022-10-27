/* 추상화된 컴포넌트들의 props 타입 */

import { ElementType } from "react";
import { CombineElementProps, OverridableProps } from "../types/utils";

export type TextBaseProps = {
    typography?: "content";
};
export type TextProps<T extends ElementType> = OverridableProps<
    T,
    TextBaseProps
>;
