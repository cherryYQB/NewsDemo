package yqb.com.example.newsdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import yqb.com.example.newsdemo.apiDemo.ApiActivity;
import yqb.com.example.newsdemo.htmlDemo.HtmlActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button1;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.button1:
                intent = new Intent(this, ApiActivity.class);
                startActivity(intent);
                break;
            case R.id.button2:
                intent = new Intent(this, HtmlActivity.class);
                startActivity(intent);
                break;
            default:
                //
        }
    }
}
