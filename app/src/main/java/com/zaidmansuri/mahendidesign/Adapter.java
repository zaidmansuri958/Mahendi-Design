package com.zaidmansuri.mahendidesign;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.viewHolder>{
    ArrayList<model> arrayList;
    Context context;

    public Adapter(ArrayList<model> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        model data=arrayList.get(position);
        Glide.with(holder.image).load(data.getImage()).into(holder.image);
        holder.title.setText(data.getTitle());
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(holder.image.getContext(),Catagory.class);
                intent.putExtra("catagory",data.getTitle());
                holder.image.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            title=itemView.findViewById(R.id.title);

        }
    }
}
