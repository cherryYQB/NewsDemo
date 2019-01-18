package yqb.com.example.newsdemo.htmlDemo;

/**
 * 首页新闻信息实体类
 */
public class NewsBean {
    private String title;
    private String href;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String url) {
        this.href = url;
    }

    @Override
    public String toString() {
        return "NewsBean{" +
                "title='" + title + '\'' +
                ", href='" + href + '\'' +
                '}';
    }
}
