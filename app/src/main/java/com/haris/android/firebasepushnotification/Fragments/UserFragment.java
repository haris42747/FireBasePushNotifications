package com.haris.android.firebasepushnotification.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.haris.android.firebasepushnotification.R;
import com.haris.android.firebasepushnotification.DataModels.Users;
import com.haris.android.firebasepushnotification.Adapters.UsersAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {

    private RecyclerView mUsersListView;
    private List<Users> usersList;
    private UsersAdapter usersAdapter;
    private FirebaseFirestore mFireStore;

    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        mFireStore = FirebaseFirestore.getInstance();

        mUsersListView = view.findViewById(R.id.users_list);

        usersList = new ArrayList<>();
        usersAdapter = new UsersAdapter(container.getContext(), usersList);

        mUsersListView.setHasFixedSize(true);
        mUsersListView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        mUsersListView.setAdapter(usersAdapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        usersList.clear();
        mFireStore.collection("Users").addSnapshotListener(getActivity(), new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {

                    if (doc.getType() == DocumentChange.Type.ADDED) {

                        String userId = doc.getDocument().getId();

                        Users users = doc.getDocument().toObject(Users.class).withId(userId);
                        usersList.add(users);

                        usersAdapter.notifyDataSetChanged();
                    }

                }

            }
        });
    }
}
