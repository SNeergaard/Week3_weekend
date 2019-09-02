package com.example.week3_weekend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.week3_weekend.model.datasource.remote.HttpUrlConnectionHelper;
import com.example.week3_weekend.model.gitUser.GitSteven;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.HttpURLConnection;

class MainActivity extends AppCompatActivity implements HttpUrlConnectionHelper.HttpCallback{
    public static final String LOG ="log";
    TextView tvLogin;
    TextView tvCreatedAt;
    TextView tvRepo;
    ImageView ivPicture;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        Log.d(LOG,"created");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvLogin = findViewById(R.id.tvLogin);
        tvCreatedAt = findViewById(R.id.tvCreatedAt);
        tvRepo = findViewById(R.id.tvRepo);
        ivPicture = findViewById(R.id.ivPicture);

        Thread networkThread=new Thread(new Runnable(){
            @Override
            public void run(){
                try{
                    HttpUrlConnectionHelper.getGitUser(MainActivity.this);
                } catch(IOException e){
                    e.printStackTrace();
                }
            }
        });
        networkThread.start();
    }

    @Override
    public void onHttpURLConnectionResponse(String json) {
        Log.d("JSON_RESPONSE","onHttpUrlConnectionResponse: -->"+json);
        final GitSteven gitSteven=new Gson().fromJson(json, GitSteven.class);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvLogin.setText(gitSteven.getLogin());
                tvRepo.setText(Integer.toString(gitSteven.getPublicRepos()));
                tvCreatedAt.setText(gitSteven.getCreatedAt());
                Picasso.get().load(gitSteven.getAvatarUrl()).into(ivPicture);
            }
        });

    }

    public void RepoIntent(View view) {
        Intent repointent = new Intent(this, ReposActivity.class);
        startActivity(repointent);
    }
}
