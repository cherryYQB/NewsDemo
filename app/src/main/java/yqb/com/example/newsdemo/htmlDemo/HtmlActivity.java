package yqb.com.example.newsdemo.htmlDemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import yqb.com.example.newsdemo.R;
import yqb.com.example.newsdemo.utils.okhttpUtils;

public class HtmlActivity extends AppCompatActivity {

    private final static String TAG = "HtmlActivity";
    private PullToRefreshListView pullToRefreshListView;
    private View headView;
    private MyGalleryView autoGallery;
    private List<NewsBean> newsBeans;
    private List<AdHeadBean> adHeadBeans;
    private final static String BASE_URL = "https://finance.sina.com.cn/";
    private final static int UPDATE_UI = 100;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_UI:
                    handler.removeMessages(UPDATE_UI);
                    bindData();
                    break;
            }
        }
    };

    private Callback okhttpCallBack = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            Toast.makeText(HtmlActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            Log.i(TAG, "===okhttp onResponse===");
            if(response.isSuccessful()) {
                String body = response.body().string();
                Document document = Jsoup.parse(body, BASE_URL);
                Log.i(TAG, "doc:"+document);

                adHeadBeans = new HeadDataManager().getHeadBeans(document);
                newsBeans = new NewsDataManager().getNewsBeans(document);
                if(adHeadBeans != null && newsBeans != null){
                    handler.sendEmptyMessage(UPDATE_UI);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html);

        initView();
    }

    public void initView() {
        headView = LayoutInflater.from(this).inflate(R.layout.autogallery_layout,null);
        pullToRefreshListView = (PullToRefreshListView) findViewById(R.id.pullToRefreshListView);
        pullToRefreshListView.addHeaderView(headView);
        autoGallery = (MyGalleryView) findViewById(R.id.gallery);

        okhttpUtils.getInstance().okhttpGetAsync(BASE_URL, okhttpCallBack);
    }

    public void bindData() {
        pullToRefreshListView.setAdapter(new MyListViewAdapter(this, newsBeans));
        List<String> urls = new ArrayList<String>();
        for(int i=0; i<adHeadBeans.size(); i++) {
            urls.add(adHeadBeans.get(i).getImgurl());
        }
        autoGallery.setUrls(urls);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(okhttpCallBack != null) {
            okhttpCallBack = null;
        }

        if(handler != null) {
            handler.removeMessages(UPDATE_UI);
            handler = null;
        }
    }
}
