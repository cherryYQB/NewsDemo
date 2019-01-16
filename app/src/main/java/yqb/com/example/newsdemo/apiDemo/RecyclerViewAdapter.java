package yqb.com.example.newsdemo.apiDemo;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import yqb.com.example.newsdemo.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private List<NewsBean> list;
    private Context mContext;

    public RecyclerViewAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext)
                    .inflate(R.layout.item_news, viewGroup, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        Glide.with(mContext).load(list.get(i).getThumbnail_pic_s()).into(viewHolder.img);
        viewHolder.title.setText(list.get(i).getTitle());
        viewHolder.author.setText(list.get(i).getAuthor_name());
        viewHolder.data.setText(list.get(i).getDate());
        final String url = list.get(i).getUrl();

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, NewsDetailActivity.class);
                intent.putExtra("url", url);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public long getItemId(int i) {
        if(list != null && list.size() > 0) {
            return i;
        }
        return 0;
    }

    @Override
    public int getItemCount() {
        if(list != null && list.size() > 0) {
            return list.size();
        }
        return 0;
    }

    class MyViewHolder extends ViewHolder
    {
        ImageView img;
        TextView title;
        TextView data;
        TextView author;

        public MyViewHolder(View view)
        {
            super(view);
            img = (ImageView) view.findViewById(R.id.img);
            title = (TextView) view.findViewById(R.id.title);
            data = (TextView) view.findViewById(R.id.data);
            author = (TextView) view.findViewById(R.id.author);
        }
    }

    public void setData(List<NewsBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
