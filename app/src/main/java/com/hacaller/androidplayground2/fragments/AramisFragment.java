package com.hacaller.androidplayground2.fragments;

import com.cleveroad.slidingtutorial.PageFragment;
import com.cleveroad.slidingtutorial.TransformItem;
import com.hacaller.androidplayground2.R;

/**
 * Created by Herbert Caller on 28/04/2016.
 */
public class AramisFragment extends PageFragment {

    @Override
    protected TransformItem[] provideTransformItems() {
        return new TransformItem[]{
                new TransformItem(R.id.img01, false, 6),
                new TransformItem(R.id.img02, true, 20),
                new TransformItem(R.id.img03, true, 2),
                new TransformItem(R.id.img04, false, 5)
        };
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_aramis;
    }
}
