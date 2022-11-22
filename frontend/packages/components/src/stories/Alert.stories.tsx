import React from "react";

import { ComponentMeta, ComponentStory } from "@storybook/react";
import { Alert } from "../components/Alert";

export default {
  title: "Alert",
  component: Alert,
} as ComponentMeta<typeof Alert>;

const Template = args => <Alert {...args} />;

export const Default: ComponentStory<typeof Alert> = Template.bind({});

Default.args = {
  severity: "error",
};
