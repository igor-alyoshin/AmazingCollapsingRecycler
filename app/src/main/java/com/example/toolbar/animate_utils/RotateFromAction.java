package com.example.toolbar.animate_utils;

import android.view.View;

import rx.functions.Action1;

/**
 * Created by igor on 07.01.16.
 */
class RotateFromAction implements Action1<View[]> {

    private final float rotation;

    public RotateFromAction(float rotation) {
        this.rotation = rotation;
    }

    @Override
    public void call(View[] views) {
        for (View v : views) {
            v.setRotation(rotation);
        }
    }
}
