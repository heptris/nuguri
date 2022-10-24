package com.ssafy.nuguri.dto.auth;

import com.ssafy.nuguri.domain.baseaddress.BaseAddress;
import com.ssafy.nuguri.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberJoinDto {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{6,16}$",
            message = "비밀번호는 6~16자리수여야 합니다. 영문 대소문자, 숫자, 특수문자를 1개 이상 포함해야 합니다.")
    private String password;

    @NotBlank
    private String passwordConfirm;

    @NotBlank
    private String name;

    @NotBlank
    @Size(min = 3, max = 30, message = "닉네임은 3~30자리수여야 합니다.")
    private String nickName;

    private String profileImage;
    private Character sex;
    private Integer age;
    private BaseAddress baseAddress;
    private Double temperature;

    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .name(name)
                .nickname(nickName)
                .profileImage(profileImage)
                .sex(sex)
                .age(age)
                .baseAddress(baseAddress)
                .temperature(temperature)
                .build();
    }

    public Member toGoogleMember() {
        return Member.builder()
                .email(email)
                .password(password)
                .name(name)
                .nickname(nickName)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }

}
