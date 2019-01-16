package yqb.com.example.newsdemo.apiDemo;

import com.google.gson.Gson;

public class GsonSingle {
    private static Gson mGson = new Gson();

    private GsonSingle() {//构造函数设置为private，防止外部程序通过new来构造
    }

    public static Gson getInstance() {
        return mGson;
    }
}
