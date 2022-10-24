import React from "react";

import { ComponentMeta, ComponentStory } from "@storybook/react";
import { Avatar } from "../Avatar";
import { JSX } from "@emotion/react/jsx-runtime";
import { AvatarClasses, SxProps, Theme } from "@mui/material";

export default {
  title: "Avatar",
  component: Avatar,
} as ComponentMeta<typeof Avatar>;

const Template = (
  args: JSX.IntrinsicAttributes & {
    alt?: string;
    children?: React.ReactNode;
    classes?: Partial<AvatarClasses>;
    imgProps?: React.ImgHTMLAttributes<HTMLImageElement> & { sx?: SxProps<Theme> };
    sizes?: string;
    src?: string;
    srcSet?: string;
    sx?: SxProps<Theme>;
    variant?: "square" | "circular" | "rounded"; //storybook.js.org/docs/react/configure/overview#configure-story-loading
  } & React.RefAttributes<HTMLDivElement>,
) => <Avatar {...args} />;

export const Default: ComponentStory<typeof Avatar> = Template.bind({});
Default.args = {
  variant: "square",
  sx: { width: 56, height: 56 },
};
