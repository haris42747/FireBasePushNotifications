package com.haris.android.firebasepushnotification.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.haris.android.firebasepushnotification.DataModels.Notifications;
import com.haris.android.firebasepushnotification.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    private List<Notifications> notificationsList;
    private Context context;
    private FirebaseFirestore firebaseFirestore;

    public NotificationAdapter(List<Notifications> notificationsList, Context context) {
        this.notificationsList = notificationsList;
        this.context = context;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notification_item, viewGroup, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NotificationViewHolder notificationViewHolder, final int i) {

        firebaseFirestore = FirebaseFirestore.getInstance();
        Notifications notifications = notificationsList.get(i);

        String from_id = notifications.getFrom();
        notificationViewHolder.notificationMessage.setText(notifications.getMessage());

        firebaseFirestore.collection("Users").document(from_id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String name = documentSnapshot.getString("name");
                String image = documentSnapshot.getString("image");

                notificationViewHolder.notificationName.setText(name);

                Glide.with(context).load(image).into(notificationViewHolder.notificationImage);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notificationsList.size();
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView notificationImage;
        private TextView notificationName;
        private TextView notificationMessage;

        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);

            notificationImage = itemView.findViewById(R.id.notification_list_image);
            notificationName = itemView.findViewById(R.id.notification_list_name);
            notificationMessage = itemView.findViewById(R.id.notification_list_message);
        }
    }

}
