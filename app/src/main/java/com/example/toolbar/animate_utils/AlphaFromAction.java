package com.example.toolbar.animate_utils;

import android.view.View;

import rx.functions.Action1;

/**
 * Created by igor on 07.01.16.
 */
class AlphaFromAction implements Action1<View[]> {

    private final float alpha;

    public AlphaFromAction(float alpha) {
        this.alpha = alpha;
    }

    @Override
    public void call(View[] views) {
        for (View v : views) {
            v.setAlpha(alpha);
        }
    }
}
