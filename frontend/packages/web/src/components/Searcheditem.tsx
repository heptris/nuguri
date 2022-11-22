import { css } from "@emotion/react";

const SearchedItem = props => {
  const { onClick, children } = props;
  return (
    <div
      css={css`
        cursor: pointer;
        font-size: 1rem;
        border-bottom: 1px black solid;
        padding-bottom: 1rem;
        margin-top: 2rem;
      `}
      onClick={onClick}
    >
      {children}
    </div>
  );
};

export default SearchedItem;
