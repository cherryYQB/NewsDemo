package yqb.com.example.newsdemo.htmlDemo;

import android.util.Log;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.List;

/**
 * 要闻边上滚动广告抓取管理
 */
class HeadDataManager {

    private static final String TAG="HeadDataManager";

    /**
     * 进行抓取滚动广告数据
     * @param document
     * @return List<AdHeadBean>
     */
    /*
    <div class="BigPic" id="BigPic_1">
     <a href="https://finance.sina.com.cn/roll/2019-01-16/doc-ihqfskcn7482671.shtml" target="_blank"><img " src="//n.sinaimg.cn/finance/transform/562/w360h202/20190116/ffNC-hrsechc3946013.jpg" galleryimg="no" alt="长安汽车销售疲软 市值蒸发近五成"></a>
     <a href="https://finance.sina.com.cn/roll/2019-01-16/doc-ihqhqcis6565148.shtml" target="_blank"><img " src="//n.sinaimg.cn/finance/transform/562/w360h202/20190116/EdIS-hrsechc4079335.jpg" galleryimg="no" alt="爱建踩雷凯迪生态：1.8亿资管逾期"></a>
     <a href="http://fund.sina.com.cn/fund/web/index?apid=62&amp;asid=csjd" target="_blank"><img src="//d1.sina.com.cn/201901/14/1545616.jpg" galleryimg="no" alt="新浪基金  海量基金一网打尽"></a>
     <a href="http://client.sina.com.cn/201901lcgc/" target="_blank"><img " src="//n.sinaimg.cn/finance/562/w360h202/20190116/eVtK-hrsechc4495084.jpg" galleryimg="no" alt="澜沧古茶 致敬时代驱动力"></a>
    </div>
     */
    public List<AdHeadBean> getHeadBeans(Document document){
        List<AdHeadBean> adHeadBeans = new ArrayList<AdHeadBean>();;
        Elements elements = document.select("div.BigPic");
        Elements links = elements.select("a");
        //Log.i(TAG, "getHeadBeans links:"+links.toString()+", linksize():"+links.size());

        for(Element element : links) {
            String href = element.attr("href");
            String imgurl = element.select("img").attr("src");
            String title = element.select("img").attr("alt");
            AdHeadBean bean = new AdHeadBean();
            bean.setTitle(title);
            bean.setHref(href);
            bean.setImgurl("http:"+imgurl);
            Log.i(TAG, "bean:"+bean.toString());
            adHeadBeans.add(bean);
        }
        return adHeadBeans;
    }

}
