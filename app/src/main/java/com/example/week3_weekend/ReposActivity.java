package com.example.week3_weekend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.week3_weekend.R;
import com.example.week3_weekend.model.datasource.remote.HttpUrlConnectionHelper;
import com.example.week3_weekend.model.gitUser.GitSteven;
import com.example.week3_weekend.model.reposit.Repository;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

public class ReposActivity extends AppCompatActivity implements HttpUrlConnectionHelper.RepoHttpCallback{
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos);
        recyclerView = findViewById(R.id.rvRepo);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        Thread networkThread=new Thread(new Runnable(){
            @Override
            public void run(){
                try{
                    HttpUrlConnectionHelper.getRepoItem(ReposActivity.this);
                } catch(IOException e){
                    e.printStackTrace();
                }
            }
        });
        networkThread.start();
    }

//    private ArrayList<Repo_Item> getRepoList() {
//        ArrayList<Repo_Item> repoList = new ArrayList<>();
//        return repoList;
//    }

    @Override
    public void onHttpURLConnectionResponse(final ArrayList<Repo_Item> json) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d("JSON_RESPONSE", "onHttpUrlConnectionResponse: -->" + json);
                RepoRVAdapter repoRVAdapter = new RepoRVAdapter(json);
                recyclerView.setAdapter(repoRVAdapter);
            }
        });

    }
}
