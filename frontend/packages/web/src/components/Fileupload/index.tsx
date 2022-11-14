import { LabelInput, Text } from "@common/components";
import { CombineElementProps } from "@common/components/src/types/utils";
import { css } from "@emotion/react";
import styled from "@emotion/styled";
import { ElementType, forwardRef, Ref, useRef, useState } from "react";
const IMAGE_FILE_EXTENSION = ".png,.jpg,.jpeg,.gif";
import ImageIcon from "@mui/icons-material/Image";

export type FileUploadBaseProps = {
  onFileSelectSuccess(file: File): void;
  onFileSelectError(error: any): void;
};
export type FileUploadProps<T extends ElementType> = CombineElementProps<T, FileUploadBaseProps>;

function FileUpload<T extends ElementType = "div">(props: FileUploadProps<T>, ref: Ref<any>) {
  const { onFileSelectSuccess, onFileSelectError, ...rest } = props;
  const fileInput = useRef<HTMLInputElement>(null);
  const [imageSrc, setImageSrc] = useState("");

  const handleFileInput = (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files[0];
    if (file) {
      if (file.size > 1024 * 1024 * 50) onFileSelectError({ error: "파일 크기가 50MB를 초과했습니다." });
      else {
        onFileSelectSuccess(file);
        setImageSrc(URL.createObjectURL(file));
      }
    } else {
      setImageSrc("");
    }
  };

  return (
    <FileUploadWrapper>
      <LabelInput type="file" css={FileUploadStyle} ref={fileInput} accept={`${IMAGE_FILE_EXTENSION}`} onChange={handleFileInput} {...rest} />
      <FileUploadBtn
        onClick={e => {
          e.preventDefault();
          fileInput.current?.click();
        }}
      >
        {imageSrc ? (
          <ImageWrapper>
            <Image alt="sample" src={imageSrc} height={50} width={50} />
          </ImageWrapper>
        ) : (
          <ImageIcon sx={{ fontSize: "30px" }} color="action" />
        )}
      </FileUploadBtn>
    </FileUploadWrapper>
  );
}

const FileUploadWrapper = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-bottom: 1rem;
`;

const FileUploadBtn = styled.button`
  display: flex;
  width: 20rem;
  height: 20rem;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  border: 0;
  border-radius: 8px;
  background-color: #b0b8c1;
  transition: box-shadow 0.3s ease 0s;

  &:hover {
    cursor: pointer;
    box-shadow: 0 0 0 2px #e5e8eb;
  }
`;

const FileUploadStyle = css`
  display: none;
`;

const ImageWrapper = styled.div`
  display: flex;
  justify-content: center;
`;

const Image = styled.img`
  width: 20rem;
  height: 20rem;
  padding: 1rem;
`;

export default forwardRef(FileUpload) as typeof FileUpload;