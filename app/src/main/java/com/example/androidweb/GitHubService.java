package com.example.androidweb;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubService {
    // GET 방식
//    내 ip 192.168.22.63
//    @GET("users/{user}/repos")
    @GET("{user}")
    Call<List<Repo>> listRepos(@Path("user") String user);
}

class Repo{  // key value 값을 짝지어 주는 것. /get set
    @SerializedName("name")
    String name;
}