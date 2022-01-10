package com.sng.retrofitgithub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView tempText;
    private TextView textViewPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewPost = findViewById(R.id.textview_post);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PlaceholderApi placeholderApi = retrofit.create(PlaceholderApi.class);

        Call<List<Post>> call = placeholderApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    textViewPost.setText("Code: " + response.code());
                    return;
                }

                List<Post> posts = response.body();

                for (Post post : posts) {
                    String postData = "";
                    postData += "ID: " + post.getId() + "\n";
                    postData += "User ID: " + post.getUserId() + "\n";
                    postData += "Title: " + post.getTitle() + "\n";
                    postData += "Description: " + post.getDescription() + "\n\n";

                    textViewPost.append(postData);
                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewPost.setText(t.getMessage());
            }
        });

    }

    public void gotoCities(View view) {
        Intent intent = new Intent(this, CitiesActivity.class);
        startActivity(intent);
    }

    public void showTemp(View view) {
        TextView tempText=(TextView)findViewById(R.id.tempText);
        tempText.setText("Temp Text");
    }
}