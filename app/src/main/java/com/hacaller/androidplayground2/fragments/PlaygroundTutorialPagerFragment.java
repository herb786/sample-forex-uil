package com.hacaller.androidplayground2.fragments;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;

import com.cleveroad.slidingtutorial.PageFragment;
import com.cleveroad.slidingtutorial.SimplePagerFragment;

/**
 * Created by Herbert Caller on 28/04/2016.
 */
public class PlaygroundTutorialPagerFragment extends SimplePagerFragment {

    @Override
    protected int getPagesCount() {
        return 3;
    }

    @Override
    protected PageFragment getPage(int position) {
        position %= 3;
        if (position == 0)
            return new AthosFragment();
        if (position == 1)
            return new PorthosFragment();
        if (position == 2)
            return new AramisFragment();
        throw new IllegalArgumentException("Unknown position: " + position);
    }

    @Override
    protected int getPageColor(int position) {
        if (position == 0)
            return ContextCompat.getColor(getContext(), android.R.color.holo_orange_dark);
        if (position == 1)
            return ContextCompat.getColor(getContext(), android.R.color.holo_green_dark);
        if (position == 2)
            return ContextCompat.getColor(getContext(), android.R.color.holo_blue_dark);
        if (position == 3)
            return ContextCompat.getColor(getContext(), android.R.color.holo_red_dark);
        if (position == 4)
            return ContextCompat.getColor(getContext(), android.R.color.holo_purple);
        if (position == 5)
            return ContextCompat.getColor(getContext(), android.R.color.darker_gray);
        return Color.TRANSPARENT;
    }

    @Override
    protected boolean isInfiniteScrollEnabled() {
        return true;
    }


}
