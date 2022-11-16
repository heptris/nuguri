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
  type?: string;
};
export type FileUploadProps<T extends ElementType> = CombineElementProps<T, FileUploadBaseProps>;

function FileUpload<T extends ElementType = "div">(props: FileUploadProps<T>, ref: Ref<any>) {
  const { onFileSelectSuccess, onFileSelectError, type, ...rest } = props;
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
      {type ? (
        <FileUploadProifleBtn
          onClick={e => {
            e.preventDefault();
            fileInput.current?.click();
          }}
        >
          {imageSrc ? (
            <ProfileImageWrapper>
              <ProfileImage alt="sample" src={imageSrc} />
            </ProfileImageWrapper>
          ) : (
            <ImageIcon sx={{ fontSize: "30px" }} color="action" />
          )}
        </FileUploadProifleBtn>
      ) : (
        <FileUploadBtn
          onClick={e => {
            e.preventDefault();
            fileInput.current?.click();
          }}
        >
          {imageSrc ? (
            <ImageWrapper>
              <Image alt="sample" src={imageSrc} />
            </ImageWrapper>
          ) : (
            <ImageIcon sx={{ fontSize: "30px" }} color="action" />
          )}
        </FileUploadBtn>
      )}
    </FileUploadWrapper>
  );
}

const FileUploadWrapper = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin: 2rem 1rem 0;
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

const FileUploadProifleBtn = styled.button`
  display: flex;
  width: 10rem;
  height: 10rem;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  border: 0;
  border-radius: 10rem;
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

const ProfileImageWrapper = styled.div`
  width: 10rem;
  height: 10rem;
`;

const ImageWrapper = styled.div`
  width: 20rem;
  height: 20rem;
`;

const Image = styled.img`
  width: 20rem;
  height: 20rem;
  border-radius: 8px;
`;

const ProfileImage = styled.img`
  width: 10rem;
  height: 10rem;
  border-radius: 10rem;
`;

export default forwardRef(FileUpload) as typeof FileUpload;
