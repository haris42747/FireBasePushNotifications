package com.haris.android.firebasepushnotification.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.haris.android.firebasepushnotification.Adapters.NotificationAdapter;
import com.haris.android.firebasepushnotification.DataModels.Notifications;
import com.haris.android.firebasepushnotification.R;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {

    private RecyclerView mNotificationList;
    private NotificationAdapter notificationAdapter;
    private List<Notifications> mNotifList;
    private FirebaseFirestore mFireStore;

    public NotificationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        mFireStore = FirebaseFirestore.getInstance();

        mNotifList = new ArrayList<>();

        mNotificationList = view.findViewById(R.id.notification_recycleView);
        notificationAdapter = new NotificationAdapter(mNotifList, getContext());

        mNotificationList.setHasFixedSize(true);
        mNotificationList.setLayoutManager(new LinearLayoutManager(container.getContext()));
        mNotificationList.setAdapter(notificationAdapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        mNotifList.clear();
        String current_user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();

        mFireStore.collection("Users").document(current_user_id).collection("Notifications").addSnapshotListener(getActivity(), new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {

                    Notifications notifications = doc.getDocument().toObject(Notifications.class);
                    mNotifList.add(notifications);

                    notificationAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
