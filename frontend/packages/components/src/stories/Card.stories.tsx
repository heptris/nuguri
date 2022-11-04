import React from "react";

import { ComponentMeta, ComponentStory } from "@storybook/react";
import { HobbyCard } from "../components/Card/HobbyCard";

export default {
  title: "Card",
  component: HobbyCard,
} as ComponentMeta<typeof HobbyCard>;

const Template = args => <HobbyCard {...args} />;

export const Default: ComponentStory<typeof HobbyCard> = Template.bind({});

Default.args = {
  variant: "outlined",
  sx: { width: 345, height: 345 },
};
