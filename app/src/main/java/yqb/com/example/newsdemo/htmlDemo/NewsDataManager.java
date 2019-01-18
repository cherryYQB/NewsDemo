package yqb.com.example.newsdemo.htmlDemo;

import android.util.Log;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.List;

/**
 * 首页新闻信息抓取管理
 */
class NewsDataManager {
    private static final String TAG="NewsDataManager";

    /**
     * 进行抓取首页信息数据
     * @param document
     * @return
     */
    /*
    <div class="m-hdline" id="blk_hdline_01" data-client="scroll p_market" data-sudaclick="blk_yw_1">
     <h3 data-client="headline"><a target="_blank" href="https://finance.sina.com.cn/world/gjcj/2019-01-16/doc-ihqfskcn7657249.shtml">特朗普妥协?暗示能与中国达成贸易协议</a></h3>
     <p data-client="throw">[<a target="_blank" href="https://finance.sina.com.cn/world/gjcj/2019-01-16/doc-ihqfskcn7580060.shtml">中美谈判关键时刻 美贸易代表办公室没钱又缺人</a> <a target="_blank" href="https://finance.sina.com.cn/roll/2019-01-16/doc-ihqfskcn7589052.shtml">中美有良好意愿</a>]</p>
     <h3 data-client="headline"><a target="_blank" href="https://finance.sina.com.cn/roll/2019-01-16/doc-ihqfskcn7589035.shtml">商务部:将进一步推进自贸区扩大开放</a></h3>
     <p data-client="throw"> [<a target="_blank" href="https://finance.sina.com.cn/china/gncj/2019-01-16/doc-ihqhqcis6632533.shtml">将推出具中国特色的自贸港政策</a> <a target="_blank" href="https://finance.sina.com.cn/china/gncj/2019-01-16/doc-ihqhqcis6619986.shtml">开展国际消费中心城市建设试点</a>]</p>
     <p data-client="throw">[<a target="_blank" href="https://finance.sina.com.cn/china/gncj/2019-01-16/doc-ihqhqcis6613088.shtml">工信部：重点落实汽车等行业开放政策</a> <a target="_blank" href="https://finance.sina.com.cn/china/gncj/2019-01-16/doc-ihqfskcn7470578.shtml">三部委勾划2019经济重点</a>]</p>
     <h3 data-client="headline"><a target="_blank" href="https://finance.sina.com.cn/money/smjj/smgq/2019-01-16/doc-ihqhqcis6702253.shtml">盛希泰:A股错过一代互联网巨头</a> <a target="_blank" href="http://finance.sina.com.cn/zt_d/kcbyth/">研讨会</a></h3>
     <p data-client="throw">[<a target="_blank" href="https://finance.sina.com.cn/stock/marketresearch/2019-01-16/doc-ihqfskcn7638328.shtml">邓庆旭:将推频道</a> <a target="_blank" href="https://finance.sina.com.cn/money/fund/2019-01-16/doc-ihqfskcn7649323.shtml">汪潮涌:腾讯阿里是中国市场永远的痛</a> <a target="_blank" class="liveNewsLeft" href="http://video.sina.com.cn/l/p/1725899.html">直播</a>]</p>
     <p data-client="throw">[<a target="_blank" href="https://finance.sina.com.cn/money/smjj/smgq/2019-01-16/doc-ihqhqcis6731919.shtml">贺强：两大问题</a> <a target="_blank" href="https://finance.sina.com.cn/money/smjj/smgq/2019-01-16/doc-ihqhqcis6745780.shtml">赵锡军:需结合多层次市场</a> <a target="_blank" href="https://finance.sina.com.cn/money/smjj/smgq/2019-01-16/doc-ihqhqcis6723880.shtml">田轩:试点同股不同权</a>]</p>
     ......
     */
    public List<NewsBean> getNewsBeans(Document document) {
        List<NewsBean> newsBeans=new ArrayList<NewsBean>();
        Elements elements = document.select("div.m-hdline");
        Elements links = elements.select("a");
        //Log.i(TAG, "getNewsBeans links:"+links.toString()+", linksize():"+links.size());

        for(Element element : links) {
            String href = element.attr("href");
            String title = element.text();
            NewsBean bean = new NewsBean();
            bean.setTitle(title);
            bean.setHref(href);
            //Log.i(TAG, "bean:"+bean.toString());
            newsBeans.add(bean);
        }
        return newsBeans;
    }
}
