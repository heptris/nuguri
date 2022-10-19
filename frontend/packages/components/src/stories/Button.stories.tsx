// Button.stories.ts|tsx

import React from "react";

import { ComponentMeta } from "@storybook/react";

import { Button } from "../Button";

export default {
  /* 👇 The title prop is optional.
   * See https://storybook.js.org/docs/react/configure/overview#configure-story-loading
   * to learn how to generate automatic titles
   */
  title: "Button",
  component: Button,
} as ComponentMeta<typeof Button>;

const Template = args => <Button {...args} />;

export const Default = Template.bind({});
Default.args = {
  task: {
    id: "1",
    title: "Test Button",
    state: "BUTTON",
  },
};
