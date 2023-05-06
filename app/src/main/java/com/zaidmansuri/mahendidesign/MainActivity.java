package com.zaidmansuri.mahendidesign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private DatabaseReference databaseReference;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycle);
        progressBar = findViewById(R.id.progress_bar);
        ArrayList<model> arrayList = new ArrayList<model>();
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#252208"));
        actionBar.setBackgroundDrawable(colorDrawable);
        progressBar.setVisibility(View.VISIBLE);

        databaseReference = FirebaseDatabase.getInstance().getReference("type");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    model data = ds.getValue(model.class);
                    arrayList.add(new model(data.getImage(), data.getTitle()));
                }

                Adapter adapter = new Adapter(arrayList, MainActivity.this);
                GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 2);
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