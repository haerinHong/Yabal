package com.example.androidweb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//2020-10-27 저녁에 공유했던 예제
//Example 1 : https://m.blog.naver.com/PostView.nhn?blogId=slavmsla&logNo=221126567021&proxyReferer=https:%2F%2Fwww.google.com%2F
// 새로 하나 찾은것 참고하면 좋음
//Example 2 : https://coding-oneday.tistory.com/18
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://127.0.0.1:8000")
                .baseUrl("http://127.0.0.1:8000/barchart/Bar-chart/") //barchart/Bar-chart
                .addConverterFactory(GsonConverterFactory.create()) //아래의 service에서 callback 받는것을 자동으로 Convert 해주게 하는것
                .build();

        GitHubService service = retrofit.create(GitHubService.class);

        try{
            service.listRepos("Bar-chart").enqueue(new Callback<List<Repo>>() {
                @Override
                public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                    List<Repo> repos = response.body();
                    for (Repo item: repos) {
                        Log.d("MainActivity" , "Retrofit2 Test : "+item.name);
                    }


                }

                @Override
                public void onFailure(Call<List<Repo>> call, Throwable t) {
                    //통신 자체가 에러났을경우
                    Log.e("MainActivity",t.getMessage());
                    Log.e("MainActivity",t.getStackTrace().toString());
                }
            });
        }catch (Exception ex){
            Log.e("MainActivity" , ex.getMessage());
            Log.e("MainActivity", ex.getLocalizedMessage());
        }




    }
}