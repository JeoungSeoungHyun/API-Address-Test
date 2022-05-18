package site.metacoding.api_address.web.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.metacoding.api_address.domain.User;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JoinReqDto {

    @NotBlank
    private String username;
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
