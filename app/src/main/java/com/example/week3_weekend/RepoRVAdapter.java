package com.example.week3_weekend;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RepoRVAdapter extends RecyclerView.Adapter<RepoRVAdapter.ViewHolder> {
    private ArrayList<Repo_Item> repo_itemArrayList;

    public RepoRVAdapter(ArrayList<Repo_Item> repo_itemArraylist) { this.repo_itemArrayList = repo_itemArraylist;}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View infatedItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repositories, parent, false);
        return new ViewHolder(infatedItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Repo_Item currentRepo_Item = repo_itemArrayList.get(position);
        holder.populateValues(currentRepo_Item);
    }

    @Override
    public int getItemCount() {return repo_itemArrayList.size();}

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvRepoName;
        private TextView tvRepoSize;
        private TextView tvRepoLang;
        private Repo_Item repoItem;

        public ViewHolder(View itemView) {
            super(itemView);
            tvRepoName = itemView.findViewById(R.id.tvRepoName);
            tvRepoSize = itemView.findViewById(R.id.tvRepoSize);
            tvRepoLang = itemView.findViewById(R.id.tvRepoLang);
        }

        public void setRepoItem(Repo_Item repoItem){
            this.repoItem = repoItem;
        }

        public void populateValues(Repo_Item repo_item){
            tvRepoName.setText(repo_item.getName());
            tvRepoSize.setText(Integer.toString(repo_item.getSize()));
            tvRepoLang.setText(repo_item.getLanguage());
        }

//        @Override
//        public void onClick(View view){
//            Intent repoIntent = new Intent(view.getContext(), MainActivity.class);
//            Bundle bundle = new Bundle();
//            bundle.putParcelable("repos", repoItem);
//            repoIntent.putExtras(bundle);
//            view.getContext().startActivity(repoIntent);
//        }
    }
}
