import React from "react";

import { ComponentMeta, ComponentStory } from "@storybook/react";
import { Tabs } from "../Tabs";

export default {
  title: "Tabs",
  component: Tabs,
} as ComponentMeta<typeof Tabs>;

const Template = args => <Tabs {...args} />;

export const Default: ComponentStory<typeof Tabs> = Template.bind({});

Default.args = {};
