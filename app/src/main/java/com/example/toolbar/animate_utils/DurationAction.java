package com.example.toolbar.animate_utils;

import android.view.View;
import android.view.ViewPropertyAnimator;

import rx.functions.Action2;

/**
 * Created by igor on 06.01.16.
 */
class DurationAction implements Action2<View, ViewPropertyAnimator> {

    private final int duration;

    public DurationAction(int duration) {
        this.duration = duration;
    }

    @Override
    public void call(View view, ViewPropertyAnimator viewPropertyAnimator) {
        viewPropertyAnimator.setDuration(duration);
    }
}
