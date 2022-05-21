package site.metacoding.login_system.web.dto.user;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InfoUpdateReqDto {

    @NotBlank
    private String address;
}
