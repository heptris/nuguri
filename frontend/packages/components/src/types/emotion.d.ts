import "@emotion/react";
import { RacconsTheme } from "../styles/theme";

declare module "@emotion/react" {
    export interface Theme extends RacconsTheme { }
}

