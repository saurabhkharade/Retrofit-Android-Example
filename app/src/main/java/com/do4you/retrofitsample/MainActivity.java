package com.do4you.retrofitsample;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<PostModel> postList;
    PostAdapter postAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recyclerview);
        postList = new ArrayList<>();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PostApi postApi = retrofit.create(PostApi.class);

        Call<List<PostModel>> call = postApi.getPosts();

        call.enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {

                if (response.code() != 200) {
                    //handle the error
                    return;
                }

                List<PostModel> postModelList = response.body();
                for (PostModel postModel : postModelList) {
                    int id = postModel.getId();
                    int userId = postModel.getUserId();
                    String title = postModel.getTitle();
                    String body = postModel.getBody();
                    Log.d("responseTest", "" + id + "--" + title);

                    postList.add(postModel);
                }

                PutDataIntoRecyclerview(postList);
            }


            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {

            }
        });


    }

    private void PutDataIntoRecyclerview(List<PostModel> postList) {
        postAdapter = new PostAdapter(this, postList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(postAdapter);
    }
}