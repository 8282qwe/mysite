package mysite.vo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {
    private Long id;

    @NotEmpty
    @Size(min = 2, max = 8)
    private String name;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Size(min = 8, max = 16)
    private String password;

    @NotEmpty
    private String gender;

    private String join_date;
    private String role;
}
