import { Card, Text } from "@common/components";
import { css } from "@emotion/react";
import styled from "@emotion/styled";

const hobby = () => {
  const newDate = new Date("2022-10-15 15:00:37");
  return (
    <MainWrapper>
      <Text
        as="p"
        css={css`
          text-align: left;
          font-size: 1.2rem;
          font-weight: 700;
        `}
      >
        취미모임
      </Text>
      <ListWrapper>
        <Card promiseDate={newDate} nowPeople={2} maxPeople={5} imgUrl={"/public/coding.jpg"} />
        <Card promiseDate={newDate} nowPeople={2} maxPeople={5} imgUrl={"/public/coding.jpg"} />
        <Card promiseDate={newDate} nowPeople={2} maxPeople={5} imgUrl={"/public/coding.jpg"} />
        <Card promiseDate={newDate} nowPeople={2} maxPeople={5} imgUrl={"/public/coding.jpg"} />
        <Card promiseDate={newDate} nowPeople={2} maxPeople={5} imgUrl={"/public/coding.jpg"} />
        <Card promiseDate={newDate} nowPeople={2} maxPeople={5} imgUrl={"/public/coding.jpg"} />
        <Card promiseDate={newDate} nowPeople={2} maxPeople={5} imgUrl={"/public/coding.jpg"} />
        <Card promiseDate={newDate} nowPeople={2} maxPeople={5} imgUrl={"/public/coding.jpg"} />
      </ListWrapper>
    </MainWrapper>
  );
};
export default hobby;

const MainWrapper = styled.div`
  max-width: 1799px;
  display: flex;
  flex-direction: column;
  padding: 1rem;
`;

const ListWrapper = styled.div`
  display: flex;
  align-items: center;
  flex-wrap: wrap;
  margin-top: 0.5rem;
  box-sizing: border-box;
`;
