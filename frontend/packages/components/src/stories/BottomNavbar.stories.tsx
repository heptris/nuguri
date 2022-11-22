import React from "react";

import { ComponentMeta, ComponentStory } from "@storybook/react";
import { BottomNavbar } from "../components/BottomNavbar";

export default {
  title: "BottomNavbar",
  component: BottomNavbar,
} as ComponentMeta<typeof BottomNavbar>;

const Template = args => <BottomNavbar {...args} />;

export const Default: ComponentStory<typeof BottomNavbar> = Template.bind({});

Default.args = {};
