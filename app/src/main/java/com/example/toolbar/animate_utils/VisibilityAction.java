package com.example.toolbar.animate_utils;

import android.view.View;

import rx.functions.Action1;

/**
 * Created by igor on 06.01.16.
 */
class VisibilityAction implements Action1<View[]> {

    private final int visibility;

    public VisibilityAction(int visibility) {
        this.visibility = visibility;
    }

    @Override
    public void call(View[] views) {
        for (View v : views) {
            v.setVisibility(visibility);
        }
    }
}
