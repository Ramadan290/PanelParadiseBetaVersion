package com.example.panelparadisebetaversion;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Home_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AniManga.
     */
    // TODO: Rename and change types and number of parameters
    public static Home_Fragment newInstance(String param1, String param2) {
        Home_Fragment fragment = new Home_Fragment();
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
        return inflater.inflate(R.layout.fragment_home,container,false);
    }
    @Override
    public void onViewCreated(@NonNull View view  , @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        Button go = view.findViewById(R.id.toluffy);
        Button go2 = view.findViewById(R.id.toerwin);
        Button go3 = view.findViewById(R.id.tozoro);
        Button go4 = view.findViewById(R.id.to_gon);
        NavController navController = Navigation.findNavController(view);
        ViewGroup parent = (ViewGroup) view.getParent();
        parent.removeView(view);



        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navController.navigate(R.id.action_home_Fragment_to_luccivluffy_vid);
                ViewGroup parent = (ViewGroup) view.getParent();
                parent.removeView(view);
            }
        });

        go2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_home_Fragment_to_erwin_vid);
                ViewGroup parent = (ViewGroup) view.getParent();
                parent.removeView(view);

            }
        });
        go3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_home_Fragment_to_zoro_vid);
                ViewGroup parent = (ViewGroup) view.getParent();
                parent.removeView(view);
            }
        });
        go4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_home_Fragment_to_gon_Fragment);
                ViewGroup parent = (ViewGroup) view.getParent();
                parent.removeView(view);
            }
        });



    }
}
