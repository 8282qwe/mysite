package mysite.vo;

public class SiteVo {
    private long id;
    private String title;
    private String welcome;
    private String profile;
    private String description;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWelcome() {
        return welcome;
    }

    public void setWelcome(String welcome) {
        this.welcome = welcome;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "SiteVo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", welcome='" + welcome + '\'' +
                ", profile='" + profile + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
