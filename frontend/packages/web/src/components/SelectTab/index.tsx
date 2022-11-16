import React, { ElementType, forwardRef, Ref, useState } from "react";

import { css, useTheme } from "@emotion/react";
import styled from "@emotion/styled";

import { CombineElementProps } from "@common/components/src/types/utils";
import { Text } from "@common/components";

/**
 * @props
 * @returns
 */

export type SelectTabBaseProps = {
  menus: string[];
  type?: "purple" | "blue";
  onSelectHandler(val: string): void;
};
export type SelectTabProps<T extends ElementType> = CombineElementProps<T, SelectTabBaseProps>;

function SelectTab<T extends ElementType = "div">(props: SelectTabProps<T>, ref: Ref<any>) {
  const { menus, type = "purple", onSelectHandler, ...rest } = props;
  const theme = useTheme();

  const [select, setSelect] = useState(menus[0]);
  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const current = e.target.value;
    setSelect(current);
    onSelectHandler(current);
  };

  return (
    <div
      css={css`
        width: 100%;
        white-space: nowrap;
      `}
    >
      <SelectTabContainer type={type} ref={ref} {...rest}>
        {menus.map((menu, i) => (
          <label
            key={i}
            css={css`
              margin: 0.5rem 0rem;
            `}
          >
            <input type="radio" value={menu} checked={select === menu} onChange={handleChange} />
            <Selects>
              <Text
                as="p"
                role="select-item"
                css={css`
                  font-size: 1.2rem;
                  font-weight: 700;
                  color: black;
                  @media screen and (max-width: 599px) {
                    font-size: 1rem;
                  }
                `}
              >
                {menu}
              </Text>
            </Selects>
          </label>
        ))}
      </SelectTabContainer>
    </div>
  );
}

const SelectTabContainer = styled.div`
  width: 100%;
  display: flex;
  flex-wrap: nowrap;
  overflow: auto;
  justify-content: center;
  align-items: center;
  padding-left: 1rem;
  -ms-overflow-style: none;
  &::-webkit-scrollbar {
    display: none; /* Chrome, Safari, Opera*/
  }
  input[type="radio"] {
    display: none;

    &:checked {
      + div {
        background-color: #b0b8c1;
      }
    }
  }
`;
const Selects = styled.div`
  height: 2.5rem;
  padding: 0 1rem;
  border-radius: 9999px;
  margin-right: 1rem;
  border: 2px solid #b0b8c1;
  display: flex;
  justify-content: center;
  align-items: center;
  transition: all 0.3s ease 0s;
  &:hover {
    cursor: pointer;
  }
  &:active {
    transform: translateY(5px);
  }
  @media screen and (max-width: 599px) {
    padding: 0 0.8rem;
  }
`;

export default forwardRef(SelectTab) as typeof SelectTab;
