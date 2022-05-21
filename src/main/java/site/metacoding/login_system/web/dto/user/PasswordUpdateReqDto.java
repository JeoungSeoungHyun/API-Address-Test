package site.metacoding.login_system.web.dto.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PasswordUpdateReqDto {

    @Size(min = 4, max = 15)
    @NotBlank
    private String password;
}
