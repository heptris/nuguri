import React from "react";

import { ComponentMeta, ComponentStory } from "@storybook/react";
import { Badge } from "../components/Badge";

export default {
  title: "Badge",
  component: Badge,
} as ComponentMeta<typeof Badge>;

const Template = args => <Badge {...args} />;

export const Default: ComponentStory<typeof Badge> = Template.bind({});

Default.args = {
  badgeContent: 4,
  color: "secondary",
};
