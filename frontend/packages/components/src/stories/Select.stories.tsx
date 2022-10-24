import React from "react";

import { ComponentMeta, ComponentStory } from "@storybook/react";
import { Select } from "../Select";

export default {
  title: "Select",
  component: Select,
} as ComponentMeta<typeof Select>;

const Template = args => <Select {...args} />;

export const Default: ComponentStory<typeof Select> = Template.bind({});

Default.args = {};
