import { css } from "@emotion/react";
import styled from "@emotion/styled";
import { useRecoilState } from "recoil";
import { ChangeEvent, useEffect, useState } from "react";
import { useRouter } from "next/router";
import { FormControl, InputLabel, MenuItem, Select, SelectChangeEvent, Box, Modal } from "@mui/material";

import { Button, LabelInput } from "@common/components";
import { ROUTES } from "@/constant";
import { useHeader, useLocation, useSearchBar, useAlert, useAuth, useBottom, useDebounce } from "@/hooks";
import { searchBarState } from "@/store";
import { apiInstance, ENDPOINT_AUTH } from "@/api";

const { HOME } = ROUTES;

const style = {
  position: "absolute" as "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 400,
  bgcolor: "background.paper",
  border: "2px solid #000",
  boxShadow: 24,
  p: 4,
};

const SignUpPage = () => {
  useHeader({ mode: "LOGIN", headingText: "회원가입" });
  useBottom(<></>);
  const { handleAlertOpen } = useAlert();
  const { handleLogin, isLoginError } = useAuth();
  const { replace } = useRouter();

  const [age, setAge] = useState(null);
  const [baseAddress, setBaseAddress] = useState("");
  const [email, setEmail] = useState("");
  const [name, setName] = useState("");
  const [nickName, setNickName] = useState("");
  const [password, setPassword] = useState("");
  const [passwordConfirm, setPasswordConfirm] = useState("");
  const [sex, setSex] = useState("");

  const [open, setOpen] = useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);
  const [searchBar, setSearchBar] = useRecoilState(searchBarState);
  const { placeholder, value } = searchBar;
  const { searchedValue } = useSearchBar("내 동네 이름(동, 읍, 면)으로 검색");
  const { handleSearchAddress, searchedData, isSearching, resetSearchedData } = useLocation();
  const debouncedSearchedValue = useDebounce(searchedValue);
  useEffect(() => {
    debouncedSearchedValue && handleSearchAddress(debouncedSearchedValue);
  }, [debouncedSearchedValue]);

  const data = {
    age,
    baseAddress,
    email,
    name,
    nickName,
    password,
    passwordConfirm,
    sex,
  };

  const onSubmitHandler = async () => {
    await apiInstance
      .post(ENDPOINT_AUTH + "/signup", data)
      .then(res => {
        console.log(res);
        handleAlertOpen("회원가입이 성공했습니다.");
        handleLogin({ email, password });
        replace(HOME);
      })
      .catch(e => {
        console.log(e);
        handleAlertOpen("회원가입이 실패했습니다.");
      });
  };

  return (
    <ContainerWrapper>
      <LabelInput
        label={"이름"}
        variant={"standard"}
        css={css`
          width: 80%;
          margin-top: 1rem;
        `}
        name={"name"}
        value={name}
        onChange={(event: ChangeEvent<HTMLInputElement>) => {
          setName(event.target.value);
        }}
      />

      <LabelInput
        label={"나이"}
        variant={"standard"}
        css={css`
          width: 80%;
          margin-top: 1rem;
        `}
        type={"number"}
        name={"age"}
        value={age}
        onChange={(event: ChangeEvent<HTMLInputElement>) => {
          setAge(event.target.value);
        }}
      />

      <Box
        sx={{ minWidth: 120 }}
        css={css`
          width: 80%;
          margin-top: 2rem;
        `}
      >
        <FormControl fullWidth>
          <InputLabel>성별</InputLabel>
          <Select
            value={sex}
            label="성별"
            onChange={(event: SelectChangeEvent) => {
              setSex(event.target.value as string);
            }}
          >
            <MenuItem value={"M"}>남자</MenuItem>
            <MenuItem value={"W"}>여자</MenuItem>
          </Select>
        </FormControl>
      </Box>

      <LabelInput
        label={"닉네임"}
        variant={"standard"}
        css={css`
          width: 80%;
          margin-top: 1rem;
        `}
        name={"nickName"}
        value={nickName}
        onChange={(event: ChangeEvent<HTMLInputElement>) => {
          setNickName(event.target.value);
        }}
      />
      <LabelInput
        label={"이메일"}
        variant={"standard"}
        css={css`
          width: 80%;
          margin-top: 1rem;
        `}
        type={"email"}
        name={"email"}
        value={email}
        onChange={(event: ChangeEvent<HTMLInputElement>) => {
          setEmail(event.target.value);
        }}
      />

      <LabelInput
        label={"내구역"}
        variant={"standard"}
        css={css`
          width: 80%;
          margin-top: 1rem;
        `}
        name={"baseAddress"}
        value={baseAddress}
        onChange={(event: ChangeEvent<HTMLInputElement>) => {
          setBaseAddress(event.target.value);
        }}
        onClick={handleOpen}
      />

      <LabelInput
        label={"비밀번호"}
        variant={"standard"}
        css={css`
          width: 80%;
          margin-top: 1rem;
        `}
        name={"password"}
        type={"password"}
        value={password}
        onChange={(event: ChangeEvent<HTMLInputElement>) => {
          setPassword(event.target.value);
        }}
      />

      <LabelInput
        label={"비밀번호확인"}
        variant={"standard"}
        css={css`
          width: 80%;
          margin-top: 1rem;
        `}
        name={"passwordConfirm"}
        type={"password"}
        value={passwordConfirm}
        onChange={(event: ChangeEvent<HTMLInputElement>) => {
          setPasswordConfirm(event.target.value);
        }}
      />

      <Button
        css={css`
          width: 80%;
          margin-top: 2rem;
          border-radius: 1rem;
        `}
        onClick={() => {
          onSubmitHandler();
        }}
      >
        회원가입
      </Button>

      <Modal open={open} onClose={handleClose} aria-labelledby="modal-modal-title" aria-describedby="modal-modal-description">
        <Box sx={style}>
          <LabelInput
            size="small"
            css={css`
              width: 90%;
            `}
            placeholder={placeholder}
            value={value}
            onChange={e => {
              setSearchBar({ placeholder, value: e.target.value });
              handleSearchAddress(debouncedSearchedValue);
            }}
          />
          <div> {!!debouncedSearchedValue ? searchedData?.message : "내 동네 검색하기"}</div>
          <div
            css={css`
              overflow-y: auto;
              height: 10rem;
            `}
          >
            {!!debouncedSearchedValue && isSearching ? (
              <div>Loading...</div>
            ) : (
              searchedData?.data.map(({ baseAddress, localId }) => {
                return (
                  <div
                    css={css`
                      cursor: pointer;
                      border: 1px black solid;
                      margin-bottom: 1rem;
                    `}
                    key={localId}
                    onClick={() => {
                      setBaseAddress(baseAddress);
                      setSearchBar({ placeholder, value: "" });
                      resetSearchedData();
                      handleClose();
                    }}
                  >
                    {baseAddress}
                  </div>
                );
              })
            )}
          </div>
        </Box>
      </Modal>
    </ContainerWrapper>
  );
};

export default SignUpPage;
const ContainerWrapper = styled.div`
  display: flex;
  width: 100%;
  height: 85vh;
  flex-direction: column;
  align-items: center;
`;
