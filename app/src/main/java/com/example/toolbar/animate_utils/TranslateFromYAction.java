package com.example.toolbar.animate_utils;

import android.view.View;

import rx.functions.Action1;

/**
 * Created by igor on 07.01.16.
 */
class TranslateFromYAction implements Action1<View[]> {

    private final Float y;

    public TranslateFromYAction(Float y) {
        this.y = y;
    }

    @Override
    public void call(View[] views) {
        for (View v : views) {
            v.setTranslationY(y);
        }
    }
}
