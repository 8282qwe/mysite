package mysite.vo;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SiteVo {
    private long id;
    private String title;
    private String welcome;
    private String profile;
    private String description;
}
