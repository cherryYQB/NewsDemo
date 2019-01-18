package yqb.com.example.newsdemo.htmlDemo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import yqb.com.example.newsdemo.R;
import yqb.com.example.newsdemo.apiDemo.NewsDetailActivity;

public class MyListViewAdapter extends BaseAdapter {

    private List<NewsBean> list;
    private Context mContext;

    public MyListViewAdapter(Context mContext, List<NewsBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public int getCount() {
        if(list != null && list.size() > 0) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        if(list != null && list.size() > 0) {
            return list.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        if(list != null && list.size() > 0) {
            return i;
        }
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder holderView = null;
        if(view == null){
            holderView = new MyViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_news_html,null);
            holderView.title = (TextView) view.findViewById(R.id.textView);
            view.setTag(holderView);
        }else {
            holderView = (MyViewHolder) view.getTag();
        }

        holderView.title.setText(list.get(i).getTitle());
        final String url = list.get(i).getHref();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //onclick
        }
        });

        return view;
    }

    class MyViewHolder {
        TextView title;
    }
}
