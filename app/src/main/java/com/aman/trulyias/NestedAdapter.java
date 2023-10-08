package com.aman.trulyias;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class NestedAdapter extends RecyclerView.Adapter<NestedAdapter.NestedViewHolder> {

    private  List<Topics> mList;

    Context context;
    public NestedAdapter(List<Topics> mList, Context context){
        this.context = context;
        this.mList = mList;
    }
    @NonNull
    @Override
    public NestedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.topic_item , parent , false);
        return new NestedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NestedViewHolder holder, int position) {
        holder.topic_name.setText(mList.get(position).getName());
        holder.topic_desc.setText(mList.get(position).getDesc());
        holder.topicImage.setImageDrawable(mList.get(position).getImg());

        holder.topicImage.setOnClickListener(v -> {
            Intent i = new Intent(context, MainActivity.class);
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public  class NestedViewHolder extends RecyclerView.ViewHolder{
        private  TextView topic_name;
        private  TextView topic_desc;
        private  ImageView topicImage;
        public NestedViewHolder(@NonNull View itemView) {
            super(itemView);
            topic_name = itemView.findViewById(R.id.topic_name);
            topicImage = itemView.findViewById(R.id.topic_image);
            topic_desc = itemView.findViewById(R.id.topic_desc);

        }
    }
}
