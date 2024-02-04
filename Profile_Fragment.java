package com.example.panelparadisebetaversion;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;

import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profile_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance()
            .getReferenceFromUrl("https://panel-paradise-beta-version-default-rtdb.firebaseio.com/");

    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    String userId;
    TextView Username, Email ;







    public Profile_Fragment() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Profile_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Profile_Fragment newInstance(String param1, String param2) {
        Profile_Fragment fragment = new Profile_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view , @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button Signout = view.findViewById(R.id.logout_btn);
        ViewGroup parent = (ViewGroup) view.getParent();
        parent.removeView(view);



        /*
        Username = view.findViewById(R.id.name_visible);
        Email= view.findViewById(R.id.Email_visible);
        userId = firebaseUser.getUid();
        databaseReference.child("Users").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Passing Data to Child
                Email =  snapshot.child("Email").getValue(TextView.class);
                Username = snapshot.child("Username").getValue(TextView.class);

                Email.setText("Email" + Email);
                Email.setVisibility(View.VISIBLE);

                Username.setText("Username" + Username);
                Username.setVisibility(View.VISIBLE);

            }

            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(),"Error", Toast.LENGTH_SHORT).show();

            }
        });
        */



        Signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
               // startActivity(new Intent(v.getContext(), Login.class));
                startActivity(new Intent(Profile_Fragment.this.getActivity(),Login.class));
                ViewGroup parent = (ViewGroup) view.getParent();
                parent.removeView(view);
            }
        });






    }
}
