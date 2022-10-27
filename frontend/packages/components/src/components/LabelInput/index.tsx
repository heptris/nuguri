import React, { forwardRef } from "react";

import { TextFieldProps } from "@mui/material";
import TextField from "@mui/material/TextField";

export const LabelInput = forwardRef<HTMLInputElement, TextFieldProps>((props, ref) => {
  return <TextField inputRef={ref} {...props} />;
});
