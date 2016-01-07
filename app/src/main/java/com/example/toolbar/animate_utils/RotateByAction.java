package com.example.toolbar.animate_utils;

import android.view.View;
import android.view.ViewPropertyAnimator;

import rx.functions.Action2;

/**
 * Created by igor on 07.01.16.
 */
class RotateByAction implements Action2<View, ViewPropertyAnimator> {

    private final float rotation;

    public RotateByAction(float rotation) {
        this.rotation = rotation;
    }

    @Override
    public void call(View view, ViewPropertyAnimator viewPropertyAnimator) {
        viewPropertyAnimator.rotationBy(rotation);
    }
}
