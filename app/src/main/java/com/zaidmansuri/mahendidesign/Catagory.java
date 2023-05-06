package com.zaidmansuri.mahendidesign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Catagory extends AppCompatActivity {
    private String catagory;
    private ArrayList<catagoryModel>arrayList;
    private DatabaseReference reference;
    private RecyclerView recyclerView;
    private ActionBar actionBar;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catagory);
        Intent intent=getIntent();
        catagory=intent.getExtras().getString("catagory");
        recyclerView=findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progress_bar);
        arrayList=new ArrayList<catagoryModel>();
        actionBar=getSupportActionBar();
        actionBar.setTitle(catagory);
        ColorDrawable colorDrawable=new ColorDrawable(Color.parseColor("#252208"));
        progressBar.setVisibility(View.VISIBLE);
        actionBar.setBackgroundDrawable(colorDrawable);
        reference= FirebaseDatabase.getInstance().getReference().child(catagory);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    catagoryModel data=ds.getValue(catagoryModel.class);
                    arrayList.add(data);
                }
                catagoryAdapter adapter=new catagoryAdapter(arrayList,Catagory.this);
                GridLayoutManager layoutManager=new GridLayoutManager(Catagory.this,2);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(layoutManager);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}