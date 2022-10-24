import React from "react";

import { ComponentMeta, ComponentStory } from "@storybook/react";
import { Menu } from "../Menu";

export default {
  title: "Menu",
  component: Menu,
} as ComponentMeta<typeof Menu>;

const Template = args => <Menu {...args} />;

export const Default: ComponentStory<typeof Menu> = Template.bind({});

Default.args = {};
