package com.example.week3_weekend.model.datasource.remote;

import android.app.Person;

import com.example.week3_weekend.Repo_Item;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class HttpUrlConnectionHelper {
    public static final String Git_URL = "https://api.github.com/users/SNeergaard";
    public static final String Repo_URL = "https://api.github.com/users/SNeergaard/repos";
    private static HttpURLConnection httpURLConnection;
    static URL url;


    public static void getGitUser(HttpCallback callback) throws IOException{
        String jsonResponse = "";
        url = new URL(Git_URL);
        httpURLConnection = (HttpURLConnection)url.openConnection();

        InputStream inputStream = httpURLConnection.getInputStream();

        int currentRead = inputStream.read();
        while (currentRead != -1){
            char currentChar = (char)currentRead;
            jsonResponse = jsonResponse + currentChar;
            currentRead = inputStream.read();
        }
        callback.onHttpURLConnectionResponse(jsonResponse);
    }

    public static void getRepoItem(RepoHttpCallback callback) throws IOException{
        url = new URL(Repo_URL);
        httpURLConnection = (HttpURLConnection)url.openConnection();
        ArrayList<Repo_Item> repoList = new ArrayList<>();
        InputStream inputStream = httpURLConnection.getInputStream();

        try{
            JsonReader reader = new JsonReader(new InputStreamReader(inputStream));
            Gson gson = new GsonBuilder().create();
            reader.beginArray();
            while (reader.hasNext()){
                Repo_Item repo_item = gson.fromJson(reader, Repo_Item.class);
                repoList.add(repo_item);
            }
        } catch (UnsupportedEncodingException ex){
        }catch (IOException ex){
        }
        callback.onHttpURLConnectionResponse(repoList);
    }

    public interface HttpCallback{
        void onHttpURLConnectionResponse(String json);

    }

    public interface RepoHttpCallback{
        void onHttpURLConnectionResponse(ArrayList<Repo_Item> json);
    }

}
