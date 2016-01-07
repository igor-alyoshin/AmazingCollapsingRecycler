package com.example.toolbar.animate_utils;

import android.view.View;
import android.view.ViewPropertyAnimator;

import rx.functions.Action2;

/**
 * Created by igor on 07.01.16.
 */
class AlphaToAction implements Action2<View, ViewPropertyAnimator> {

    private final float alpha;

    public AlphaToAction(float alpha) {
        this.alpha = alpha;
    }

    @Override
    public void call(View view, ViewPropertyAnimator viewPropertyAnimator) {
        viewPropertyAnimator.alpha(alpha);
    }
}
