package mysite.vo;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardVo {
    private Long id;
    private String title;
    private String contents;
    private int hit;
    private String reg_date;
    private int g_no;
    private int o_no;
    private int depth;
    private Long user_id;
    private String user_name;
}
