package me.prokawsar.madrashamanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class SplashScreen extends AppCompatActivity {
    private ProgressBar progressBar;
    private int progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_splash_screen);

        progressBar = findViewById(R.id.progressId);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
                startActivity(new Intent(SplashScreen.this,MainActivity.class));
                finish();
            }
        });
        thread.start();
    }

    private void doWork() {
        for (progress = 30;progress <=100;progress = progress+30){
            try {
                Thread.sleep(1000);
                progressBar.setProgress(progress);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
