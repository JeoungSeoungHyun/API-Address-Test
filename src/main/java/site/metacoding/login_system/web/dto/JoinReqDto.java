package site.metacoding.login_system.web.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.metacoding.login_system.domain.User;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JoinReqDto {

    @Size(min = 4, max = 15)
    @NotBlank
    @Pattern(regexp = "[a-zA-Z0-9]{4,20}", message = "유저네임에 한글이 들어갈 수 없습니다.")
    private String username;

    @Size(min = 4, max = 15)
    @NotBlank
    private String password;

    @NotBlank
    private String address;

    public User toEntity() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setAddress(address);

        return user;
    }
}
