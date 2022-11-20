package com.ssafy.nuguri.controller.auth;

import com.ssafy.nuguri.dto.auth.MemberJoinDto;
import com.ssafy.nuguri.dto.auth.MemberLoginDto;
import com.ssafy.nuguri.dto.response.ResponseDto;
import com.ssafy.nuguri.dto.token.TokenDto;
import com.ssafy.nuguri.dto.token.TokenRequestDto;
import com.ssafy.nuguri.exception.ex.CustomException;
import com.ssafy.nuguri.exception.ex.CustomValidationException;
import com.ssafy.nuguri.service.member.AuthService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import static com.ssafy.nuguri.exception.ex.ErrorCode.NOT_EQUAL_PASSWORD;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid MemberJoinDto memberJoinDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }

            throw new CustomValidationException("유효성 검사 실패", errorMap);
        } else {
            if (!memberJoinDto.getPassword().equals(memberJoinDto.getPasswordConfirm())) {
                throw new CustomException(NOT_EQUAL_PASSWORD);
            }
            return new ResponseEntity<ResponseDto>(new ResponseDto(200, "회원가입 성공",
                    authService.signup(memberJoinDto)), HttpStatus.OK);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberLoginDto memberLoginDto) {
        TokenDto token = authService.login(memberLoginDto);
        return new ResponseEntity<ResponseDto>(new ResponseDto<>(200, "로그인 성공",
                token), HttpStatus.OK);
    }

    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return new ResponseEntity<ResponseDto>(new ResponseDto<>(200, "토큰 재발행",
                authService.reissue(tokenRequestDto)), HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity logout(HttpServletRequest request) {
        String accessToken = request.getHeader("Authorization").substring(7);
        authService.logout(accessToken);
        return new ResponseEntity<>(new ResponseDto<>(200, "로그아웃 완료", null)
                , HttpStatus.OK);
    }
}
