package com.example.panelparadisebetaversion;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link gon_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class gon_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public gon_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment gon_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static gon_Fragment newInstance(String param1, String param2) {
        gon_Fragment fragment = new gon_Fragment();
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
                             Bundle savedInstanceState) throws NullPointerException{
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_gon_,container,false);
        MediaController mc = new MediaController(getActivity());

        VideoView view = (VideoView)rootView.findViewById(R.id.gonvpitou);
        String path = "android.resource://" +getActivity().getPackageName() + "/" + R.raw.animanga4  ;
        view.setVideoURI(Uri.parse(path));
        view.setMediaController(mc);
        view.start();
        ViewGroup parent = (ViewGroup) view.getParent();
        parent.removeView(view);
        return view;

    }
    @Override
    public void onViewCreated(@NonNull View view , @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        NavController navController = Navigation.findNavController(view);
        ViewGroup parent = (ViewGroup) view.getParent();
        parent.removeView(view);



    }
}