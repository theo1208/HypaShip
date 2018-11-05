package com.hypa.hypaship.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.hypa.hypaship.activitites.MainActivity;
import com.hypa.hypaship.interfaces.ActivityListener;
import com.hypa.hypaship.interfaces.FragmentListener;

public class BaseFragment extends Fragment implements ActivityListener {

    public FragmentListener fragmentListener;

    public String fragmentTitle;

    public int fragmentID;

    public void setActionBarTitle()
    {
        fragmentTitle=getString(fragmentID);
        fragmentListener.setTitle(fragmentTitle);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            fragmentListener = (MainActivity) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement FragmentListener");
        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setActionBarTitle();
    }

    @Override
    public void onBackPressed() {

    }
}
