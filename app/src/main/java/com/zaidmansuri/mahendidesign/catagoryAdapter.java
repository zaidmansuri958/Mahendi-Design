package com.zaidmansuri.mahendidesign;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class catagoryAdapter extends RecyclerView.Adapter<catagoryAdapter.viewHolder> {
    ArrayList<catagoryModel> arr;
    Context context;

    public catagoryAdapter(ArrayList<catagoryModel> arr, Context context) {
        this.arr = arr;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.catagorycard,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        catagoryModel data= arr.get(position);
        Glide.with(holder.image).load(data.getImg()).into(holder.image);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,detail.class);
                intent.putExtra("url",data.getImg());
                holder.image.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.img);
        }
    }
}
