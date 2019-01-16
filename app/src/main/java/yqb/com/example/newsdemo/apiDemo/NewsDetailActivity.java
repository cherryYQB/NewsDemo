package yqb.com.example.newsdemo.apiDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import yqb.com.example.newsdemo.R;

public class NewsDetailActivity extends AppCompatActivity {

    private WebView webView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
        webView = (WebView) findViewById(R.id.webview);

        String url = getIntent().getStringExtra("url");
        Log.i("TAG", "url:"+url);
        webView.loadUrl(url);

        WebSettings webSettings=webView.getSettings();
        //webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);//不使用缓存，只从网络获取数据.
        //支持屏幕缩放
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        /*fix bug:android.widget.ZoomButtonsController,that was originally registered here.
         Are you missing a call to unregisterReceiver()
          */
        ViewGroup view = (ViewGroup) getWindow().getDecorView();
        view.removeAllViews();

        if(webView != null) {
            webView.destroy();
            webView = null;
        }
    }
}
