package yqb.com.example.newsdemo.htmlDemo;

/**
 * 要闻边上滚动广告
 */
public class AdHeadBean {

    private String title; // 标题
    private String imgurl; // 图片地址
    private String href; // 文章详情地址

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public String toString() {
        return "AdHeadBean{" +
                "title='" + title + '\'' +
                ", imgurl='" + imgurl + '\'' +
                ", href='" + href + '\'' +
                '}';
    }
}
