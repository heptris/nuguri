import { useHeader } from "@/hooks";
import { Button } from "@common/components";
import React, { useState } from "react";

const PostButton = ({ disabled, onClickPostButton }) => {
  return (
    <Button disabled={disabled} onClick={onClickPostButton}>
      완료
    </Button>
  );
};

const PostPage = () => {
  const [disabled, setDisabled] = useState(true);
  const onClickPostButton = () => {};
  useHeader({ mode: "POST", HeaderRight: <PostButton {...{ disabled, onClickPostButton }} /> });
  return <div>PostPage</div>;
};

export default PostPage;
