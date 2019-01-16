package yqb.com.example.newsdemo.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import yqb.com.example.newsdemo.apiDemo.ApiActivity;
import yqb.com.example.newsdemo.apiDemo.NewsFragment;

public class okhttpUtils {

    private OkHttpClient client;
    private HttpLoggingInterceptor loggingInterceptor;

    private okhttpUtils() {
        if(client == null) {
            client = new OkHttpClient();
        }
        if(loggingInterceptor == null) {
            loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }
    }

    public static okhttpUtils getInstance() {
        return okhttpSingleHolder.okhttp;
    }

    private static class okhttpSingleHolder {
        private static final okhttpUtils okhttp = new okhttpUtils();
    }


    public void okhttpPostAsync(Context context, final String url, final Map<String, String> map,
                           File file, Callback callback) {

        // form 表单形式上传
        MultipartBody.Builder requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);

        if(file != null){
            // MediaType.parse() 里面是上传的文件类型。
            RequestBody body = RequestBody.create(MediaType.parse("image*//*"), file);
            String filename = file.getName();
            // 参数分别为， 请求key ，文件名称 ， RequestBody
            requestBody.addFormDataPart("header_image", file.getName(), body);
        }

        if (map != null) {
            // map 里面是请求中所需要的 key 和 value
            for (Map.Entry entry : map.entrySet()) {
                requestBody.addFormDataPart(String.valueOf(entry.getKey()),
                        String.valueOf(entry.getValue()));
            }
        }

        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .post(requestBody.build())
                .tag(context)
                .build();

        // readTimeout("请求超时时间" , 时间单位)
        client.newBuilder()
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor)
                .build()
                .newCall(request)
                .enqueue(callback);
    }

    public void okhttpGetAsync(String url, Callback callback) {
        final Request request=new Request.Builder().url(url).build();
        client.newCall(request).enqueue(callback);
    }
}
