/* 테마 모듈 */

export interface RacconsTheme {
  name: string;
  color: {
    background: {
      main: string;
      submain: string;
      emphasis: string;
      page: string;
      card: string;
      item: string;
    };
    hover: {
      main: string;
      emphasis: string;
    };
    text: {
      main: string;
      sub: string;
      hover: string;
    };
    status: {
      success: string;
      fail: string;
      disabled: string;
    };
    border: {
      main: string;
    };
  };
}
const darkTheme: RacconsTheme = {
  name: "darkTheme",
  color: {
    background: {
      main: "var(--main)",
      submain: "var(--white)",
      emphasis: "var(--vividBlue)",
      page: "var(--ink900)",
      card: "var(--ink800)",
      item: "var(--ink700)",
    },
    hover: {
      main: "var(--purple500)",
      emphasis: "var(--blue900)",
    },
    text: {
      main: "var(--black)",
      sub: "var(--ink300)",
      hover: "var(--ink200)",
    },
    status: {
      success: "var(--vividGreen)",
      fail: "var(--vividPink)",
      disabled: "var(--disabled)",
    },
    border: {
      main: "var(--ink600)",
    },
  },
};
export const racconsThemes = { darkTheme };
