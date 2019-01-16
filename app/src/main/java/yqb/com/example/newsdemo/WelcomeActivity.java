package yqb.com.example.newsdemo;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class WelcomeActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener, View.OnClickListener, MediaPlayer.OnPreparedListener {

    private VideoView videoView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        videoView = (VideoView) findViewById(R.id.videoView);
        button = (Button) findViewById(R.id.welcome_button);

        videoView.setOnPreparedListener(this);
        videoView.setVideoURI(Uri.parse("android.resource://"+this.getPackageName()+"/"+R.raw.welcome));
        videoView.start();
        videoView.setOnCompletionListener(this);
        button.setOnClickListener(this);
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.setVideoScalingMode(MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        videoView.start();
    }

    @Override
    public void onClick(View view) {
        if(videoView.isPlaying()) {
            videoView.stopPlayback();
            videoView = null;
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(videoView != null && videoView.isPlaying()) {
            videoView.stopPlayback();
            videoView = null;
        }
    }
}
