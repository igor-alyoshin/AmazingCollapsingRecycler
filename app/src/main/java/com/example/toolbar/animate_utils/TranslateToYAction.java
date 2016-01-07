package com.example.toolbar.animate_utils;

import android.view.View;
import android.view.ViewPropertyAnimator;

import rx.functions.Action2;

/**
 * Created by igor on 06.01.16.
 */
class TranslateToYAction implements Action2<View, ViewPropertyAnimator> {

    private final Float y;

    public TranslateToYAction(Float y) {
        this.y = y;
    }

    @Override
    public void call(View view, ViewPropertyAnimator animator) {
        animator.translationY(y);
    }
}
