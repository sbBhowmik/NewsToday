package com.rupik.newstoday;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import se.emilsjolander.flipview.FlipView;
import se.emilsjolander.flipview.OverFlipMode;

/**
 * Created by macmin5 on 03/02/17.
 */

public class SuperAwesomeCardFragment extends Fragment{
    private static final String ARG_POSITION = "position";

    private int position;

    private FlipView mFlipView;
    private FlipAdapter mAdapter;

    public static SuperAwesomeCardFragment newInstance(int position) {
        SuperAwesomeCardFragment f = new SuperAwesomeCardFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt(ARG_POSITION);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.pager_item,container,false);
        ViewCompat.setElevation(rootView, 50);
        TextView textView = (TextView) rootView.findViewById(R.id.textView);
        textView.setText("CARD " + position);
//Flip View

        mFlipView = (FlipView) rootView.findViewById(R.id.flip_view);
        mAdapter = new FlipAdapter(getActivity());
//        mAdapter.setCallback(getActivity());
        mFlipView.setAdapter(mAdapter);
//        mFlipView.setOnFlipListener(this);
        mFlipView.peakNext(true);
        mFlipView.setOverFlipMode(OverFlipMode.RUBBER_BAND);
        mFlipView.setEmptyView(rootView.findViewById(R.id.empty_view));
//        mFlipView.setOnOverFlipListener(this);

        return rootView;
    }
}
