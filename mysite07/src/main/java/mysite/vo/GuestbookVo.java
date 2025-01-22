package mysite.vo;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GuestbookVo {
    private Long id;
    private String name;
    private String password;
    private String regDate;
    private String contents;
}
