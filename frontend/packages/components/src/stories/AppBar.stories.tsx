import React from "react";

import { ComponentMeta, ComponentStory } from "@storybook/react";
import { AppBar } from "../components/AppBar";

export default {
  title: "AppBar",
  component: AppBar,
} as ComponentMeta<typeof AppBar>;

const Template = args => <AppBar {...args} />;

export const Default: ComponentStory<typeof AppBar> = Template.bind({});

Default.args = {};
