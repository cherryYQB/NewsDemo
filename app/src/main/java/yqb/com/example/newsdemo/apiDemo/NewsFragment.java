package yqb.com.example.newsdemo.apiDemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import yqb.com.example.newsdemo.R;
import yqb.com.example.newsdemo.utils.okhttpUtils;

public class NewsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private final static String BASE_URL = "http://v.juhe.cn/toutiao/index";
    private final static String UNI_KEY = "93a3455265ba4a70479d418021fd732c";
    private Map<String, String> map = new HashMap<String, String>();
    List<NewsBean> newsList = new ArrayList<NewsBean>();
    private final static int UPDATE_UI = 100;
    private String mType = ApiActivity.NEWS_TYPE_TOP;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_UI:
                    handler.removeMessages(UPDATE_UI);
                    updateUI();
                    break;
            }
        }
    };

    private Callback okhttpCallBack = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            Toast.makeText(getActivity(), "网络连接失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            if(response.isSuccessful()) {
                JsonParser parser = new JsonParser();
                JsonObject jsonObject = parser.parse(response.body().string()).getAsJsonObject();
                JsonObject object = jsonObject.get("result").getAsJsonObject();
                JsonArray jsonArray = object.get("data").getAsJsonArray();
                /*for(int i=0; i<jsonArray.size(); i++) {
                    NewsBean newsBean = GsonSingle.getInstance().fromJson(jsonArray.get(i), NewsBean.class);
                    newsList.add(newsBean);
                }*/
                newsList = GsonSingle.getInstance().fromJson(jsonArray,
                        new TypeToken<List<NewsBean>>() {}.getType());
                handler.sendEmptyMessage(UPDATE_UI);
            }
        }
    };

    public static NewsFragment newInstance(String type) {
        Bundle args = new Bundle();
        NewsFragment fragment = new NewsFragment();
        args.putString("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = getArguments().getString("type");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, null);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light);
        swipeRefreshLayout.setOnRefreshListener(this);

        //设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //设置adapter
        if(recyclerViewAdapter == null) {
            recyclerViewAdapter = new RecyclerViewAdapter(getActivity());
        }
        recyclerView.setAdapter(recyclerViewAdapter);

        map.put("type", mType);
        map.put("key", UNI_KEY);

        loadNews();
        return view;
    }

    @Override
    public void onRefresh() {
        loadNews();
    }

    public void loadNews() {
        okhttpUtils.getInstance().okhttpPostAsync(getActivity(), BASE_URL,
                map, null, okhttpCallBack);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(okhttpCallBack != null) {
            okhttpCallBack = null;
        }

        if(handler != null) {
            handler.removeMessages(UPDATE_UI);
            handler = null;
        }
    }

    /**
     *
     */
    public void updateUI() {
        recyclerViewAdapter.setData(newsList);
        if(swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}
