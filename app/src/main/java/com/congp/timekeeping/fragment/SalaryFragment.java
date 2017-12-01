package com.congp.timekeeping.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.congp.timekeeping.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SalaryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SalaryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SalaryFragment extends Fragment {

    public static SalaryFragment newInstance() {
        SalaryFragment fragment = new SalaryFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_salary, container, false);
    }


}
