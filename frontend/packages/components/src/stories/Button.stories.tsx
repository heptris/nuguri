// Button.stories.ts|tsx

import React from "react";

import { ComponentMeta, ComponentStory } from "@storybook/react";

import { Button } from "../components/Button";
import { JSX } from "@emotion/react/jsx-runtime";
import { ButtonTypeMap } from "@mui/material";

export default {
  /* 👇 The title prop is optional.
   * See https://storybook.js.org/docs/react/configure/overview#configure-story-loading
   * to learn how to generate automatic titles
   */
  title: "Button",
  component: Button,
} as ComponentMeta<typeof Button>;

const Template = (args: JSX.IntrinsicAttributes & ButtonTypeMap<{}, "button"> & React.RefAttributes<HTMLButtonElement>) => <Button {...args} />;

export const Default: ComponentStory<typeof Button> = Template.bind({});
Default.args = {
  color: "primary",
  children: "테스트",
  variant: "contained",
};
