package com.example.toolbar.animate_utils;

import android.view.View;

import rx.functions.Action1;

/**
 * Created by igor on 07.01.16.
 */
class EnabledAction implements Action1<View[]> {

    private final boolean enabled;

    public EnabledAction(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public void call(View[] views) {
        for (View v : views) {
            v.setEnabled(enabled);
        }
    }
}
