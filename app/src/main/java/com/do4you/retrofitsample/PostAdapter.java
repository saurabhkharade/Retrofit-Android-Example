package com.do4you.retrofitsample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {


    Context context;
    private List<PostModel> postModelList;

    public PostAdapter(Context context, List<PostModel> postModelList) {
        this.context = context;
        this.postModelList = postModelList;
    }

    @NonNull
    @Override
    public PostAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.post_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.MyViewHolder holder, int position) {

        PostModel postModel = postModelList.get(position);

        String str_user_id = String.valueOf(postModel.getUserId());
        String str_id = String.valueOf(postModel.getId());

        holder.user_id.setText("User Id: "+str_user_id);
        holder.id.setText("ID"+str_id);
        holder.title.setText("Title: "+postModel.getTitle());
        holder.body.setText("Body: "+postModel.getBody());

    }

    @Override
    public int getItemCount() {
        return postModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView user_id, id, title, body;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            user_id = itemView.findViewById(R.id.user_id);
            id = itemView.findViewById(R.id.id);
            title = itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.body);
        }
    }
}
