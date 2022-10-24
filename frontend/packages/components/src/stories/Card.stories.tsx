import React from "react";

import { ComponentMeta, ComponentStory } from "@storybook/react";
import { Card } from "../Card";

export default {
  title: "Card",
  component: Card,
} as ComponentMeta<typeof Card>;

const Template = args => <Card {...args} />;

export const Default: ComponentStory<typeof Card> = Template.bind({});

Default.args = {
  variant: "outlined",
  sx: { width: 345, height: 345 },
};
