package com.haris.android.firebasepushnotification.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.haris.android.firebasepushnotification.DataModels.Users;
import com.haris.android.firebasepushnotification.R;
import com.haris.android.firebasepushnotification.Activities.SendActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {

    private List<Users> usersList;
    private Context context;

    public UsersAdapter(Context context, List<Users> usersList) {
        this.usersList = usersList;
        this.context = context;
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_list_item, viewGroup, false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder usersViewHolder, int i) {
        Users users = usersList.get(i);

        final String user_name = users.getName();

        usersViewHolder.userName.setText(user_name);
        Glide.with(context).load(users.getImage()).into(usersViewHolder.userImageView);

        final String user_Id = users.userId;

        usersViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(context, SendActivity.class);
                sendIntent.putExtra("user_id", user_Id);
                sendIntent.putExtra("user_name", user_name);
                context.startActivity(sendIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class UsersViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView userImageView;
        private TextView userName;

        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);

            userImageView = itemView.findViewById(R.id.user_list_image);
            userName = itemView.findViewById(R.id.user_list_name);
        }
    }

}
