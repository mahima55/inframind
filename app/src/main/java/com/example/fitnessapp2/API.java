package com.example.fitnessapp2;

import android.content.ClipData;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class API {
    private static final String key="381067659346-n37afjvgilgeqsuu4uhmhecdfaf934hg.apps.googleusercontent.com";
    private static final String url="https://www.googleapis.com/fitness/v1/users/hp/dataset:aggregate/";

    public static PostService postService = null;

    public static PostService getService(){
        if(postService == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            postService = retrofit.create(PostService.class);
        }
        return postService;
    }

    public interface PostService {
        @GET("?key=" + key)
        Call<PostList> getPostList();

    }

}
