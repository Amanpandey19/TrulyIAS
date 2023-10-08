package com.aman.trulyias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    RecyclerView recyclerView;
    private static final String SHARED_PREF_NAME = "mypref";


    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView = findViewById(R.id.main_recyclerview);
        setUpRecyclerView();
        setDetails();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void setUpRecyclerView()
    {
        ArrayList<Topics> topicsList = new ArrayList<>();
        List<Chapters> chapters = new ArrayList<>();

        topicsList.add(new Topics("Basics of Science", "Covers all the basics of Science needed to study further chapters",
                getDrawable(R.drawable.im1)));
        topicsList.add(new Topics("Basics of Science", "Covers all the basics of Science needed to study further chapters",
                getDrawable(R.drawable.im2)));
        topicsList.add(new Topics("Basics of Science", "Covers all the basics of Science needed to study further chapters",
                getDrawable(R.drawable.im3)));
        topicsList.add(new Topics("Basics of Science", "Covers all the basics of Science needed to study further chapters",
                getDrawable(R.drawable.im1)));
        topicsList.add(new Topics("Basics of Science", "Covers all the basics of Science needed to study further chapters",
                getDrawable(R.drawable.im2)));
        topicsList.add(new Topics("Basics of Science", "Covers all the basics of Science needed to study further chapters",
                getDrawable(R.drawable.im3)));

        chapters.add(new Chapters("Chapter 1 : Food: Where Does it Come From", topicsList));
        chapters.add(new Chapters("Chapter 2 : Getting to Know Plants", topicsList));
        chapters.add(new Chapters("Chapter 3 : Electricity and Circuits", topicsList));
        chapters.add(new Chapters("Chapter 4 : Body Movements", topicsList));
        chapters.add(new Chapters("Chapter 5 : Fun with Magnets", topicsList));
        chapters.add(new Chapters("Chapter 6 : Components of Food", topicsList));
        chapters.add(new Chapters("Chapter 7 : Air Around Us", topicsList));
        chapters.add(new Chapters("Chapter 8 : Separation of Substances", topicsList));
        chapters.add(new Chapters("Chapter 9 : Light, Shadow and Reflections", topicsList));


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ItemAdapter adapter = new ItemAdapter(chapters, this);
        recyclerView.setAdapter(adapter);


    }

    @SuppressLint("SetTextI18n")
    private void setDetails() {
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);

        String name         = sharedPreferences.getString("name","");
        String standard     = sharedPreferences.getString("standard","");

        TextView userName   = findViewById(R.id.user_name);
        TextView userClass  = findViewById(R.id.user_class);
        userName.setText("Hi, "+name);
        userClass.setText("Class : "+ standard);


    }
}