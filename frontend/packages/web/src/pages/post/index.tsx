import Link from "@/components/Link";
import { ROUTES } from "@/constant";
import { useHeader } from "@/hooks";
import withAuth from "@/utils/withAuth";
import { Button } from "@common/components";
import React, { useState } from "react";

const PostButton = ({ disabled, onClickPostButton }) => {
  return (
    <Button disabled={disabled} onClick={onClickPostButton}>
      완료
    </Button>
  );
};

const { POST } = ROUTES;

const PostPage = () => {
  const [disabled, setDisabled] = useState(true);
  const onClickPostButton = () => {};
  useHeader({ mode: "POST", HeaderRight: <PostButton {...{ disabled, onClickPostButton }} /> });

  return (
    <div>
      취미 카테고리 선택 후 서비스 종류 선택이 가능합니다.
      <select>
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
        <option value="5">5</option>
      </select>
      <div>
        <Link href={POST + "/hobby"}>
          <Button>취미모임</Button>
        </Link>
        <Link href={POST + "/deal"}>
          <Button>중고거래</Button>
        </Link>
        <Link href={POST + "/grppurch"}>
          <Button>공구거래</Button>
        </Link>
      </div>
    </div>
  );
};

export default withAuth(PostPage);
