package site.metacoding.login_system.web.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDetailRespDto {

    private Integer id;
    private String username;
    private String address;
    private String profileImg;
}
