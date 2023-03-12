package com.example.habittrackerprojectjavaversion.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.habittrackerprojectjavaversion.R;
import com.example.habittrackerprojectjavaversion.data.PetModel;

import java.util.ArrayList;

public class Pet_RecycleViewAdapter extends RecyclerView.Adapter<Pet_RecycleViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<PetModel> petModels;
    public Pet_RecycleViewAdapter(Context context, ArrayList<PetModel> petModels){
        this.context = context;
        this.petModels = petModels;
    }
    @NonNull
    @Override
    public Pet_RecycleViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_petrow,parent,false);

        return new Pet_RecycleViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Pet_RecycleViewAdapter.MyViewHolder holder, int position) {
        holder.tvName.setText(petModels.get(position).getPetname());
        holder.tvsound.setText(petModels.get(position).getPetsound());
        holder.imageView.setImageResource(petModels.get(position).getPetimage());
    }

    @Override
    public int getItemCount() {
        return petModels.size();
    }

    public static  class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView ;
        TextView tvName,tvsound;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView3);
            tvName = itemView.findViewById(R.id.animaltextname);
            tvsound = itemView.findViewById(R.id.animaltextsound);
        }

    }
}