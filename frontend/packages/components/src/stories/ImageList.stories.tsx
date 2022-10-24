import React from "react";

import { ComponentMeta, ComponentStory } from "@storybook/react";
import { ImageList } from "../ImageList";

export default {
  title: "ImageList",
  component: ImageList,
} as ComponentMeta<typeof ImageList>;

const Template = args => <ImageList {...args} />;

export const Default: ComponentStory<typeof ImageList> = Template.bind({});

Default.args = {
  sx: { width: 500, height: 450 },
  cols: 3,
  rowHeight: 164,
};
