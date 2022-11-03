import React from "react";

import { ComponentMeta, ComponentStory } from "@storybook/react";
import { Modal } from "../components/Modal";

export default {
  title: "Modal",
  component: Modal,
} as ComponentMeta<typeof Modal>;

const Template = args => <Modal {...args} />;

export const Default: ComponentStory<typeof Modal> = Template.bind({});

Default.args = {
  "aria-labelledby": "modal-modal-title",
  "aria-describedby": "modal-modal-description",
};
