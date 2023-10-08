package com.aman.trulyias;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private final List<Chapters> mList;
    private List<Topics> list = new ArrayList<>();

    Context context;

    public ItemAdapter(List<Chapters> mList, Context context){
        this.mList  = mList;
        this.context = context;
    }
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.chapter_item , parent , false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        Chapters model = mList.get(position);
        holder.mTextView.setText(model.getName());

        boolean isExpandable = model.isExpandable();
        holder.expandableLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);

        NestedAdapter adapter = new NestedAdapter(list, context);

        holder.nestedRecyclerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext(),  RecyclerView.HORIZONTAL, false));
        holder.nestedRecyclerView.setHasFixedSize(true);
        holder.nestedRecyclerView.setAdapter(adapter);

        holder.linearLayout.setOnClickListener(v -> {
            model.setExpandable(!model.isExpandable());
            list = model.getTopicsArrayList();
            notifyItemChanged(holder.getAdapterPosition());
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        private final LinearLayout linearLayout;
        private final RelativeLayout expandableLayout;
        private final TextView mTextView;
        private final RecyclerView nestedRecyclerView;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.linear_layout);
            expandableLayout = itemView.findViewById(R.id.expandable_layout);
            mTextView = itemView.findViewById(R.id.chapter_name);
            nestedRecyclerView = itemView.findViewById(R.id.child_rv);
        }
    }
}