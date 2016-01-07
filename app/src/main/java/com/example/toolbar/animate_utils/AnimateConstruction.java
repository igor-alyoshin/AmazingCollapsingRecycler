package com.example.toolbar.animate_utils;

import android.animation.Animator;
import android.view.View;
import android.view.ViewPropertyAnimator;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Action2;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;

/**
 * Created by igor on 06.01.16.
 */
public class AnimateConstruction {

    private final Map<View, ReplaySubject<Action2<View, ViewPropertyAnimator>>> subjects = new HashMap<>();
    private final PublishSubject<Void> postAnimationSubject = PublishSubject.create();
    private final PublishSubject<Void> preAnimationSubject = PublishSubject.create();

    public static AnimateConstruction create() {
        return new AnimateConstruction();
    }
    
    protected AnimateConstruction() {
    }

    public AnimateConstruction translateToY(float y, View... views) {
        return addAnimationAction(new TranslateToYAction(y), views);
    }

    public AnimateConstruction rotateBy(float y, View... views) {
        return addAnimationAction(new RotateByAction(y), views);
    }

    public AnimateConstruction translateFromY(final float y, View... views) {
        return addPreAnimationAction(new TranslateFromYAction(y), views);
    }

    public AnimateConstruction rotateFrom(final float rotation, View... views) {
        return addPreAnimationAction(new RotateFromAction(rotation), views);
    }

    public AnimateConstruction enabledFrom(final boolean enabled, View... views) {
        return addPreAnimationAction(new EnabledAction(enabled), views);
    }

    public AnimateConstruction enabledTo(final boolean enabled, View... views) {
        return addPostAnimationAction(new EnabledAction(enabled), views);
    }

    public AnimateConstruction alphaFrom(final float alpha, View... views) {
        return addPreAnimationAction(new AlphaFromAction(alpha), views);
    }

    public AnimateConstruction alphaTo(final float alpha, View... views) {
        return addAnimationAction(new AlphaToAction(alpha), views);
    }

    public AnimateConstruction visibilityFrom(final int visibility, View... views) {
        return addPreAnimationAction(new VisibilityAction(visibility), views);
    }

    public AnimateConstruction duration(final int duration, View... views) {
        return addAnimationAction(new DurationAction(duration), views);
    }

    public AnimateConstruction visibilityTo(final int visibility, View... views) {
        return addPostAnimationAction(new VisibilityAction(visibility), views);
    }
    
    public void animate() {
        preAnimationSubject.onCompleted();
        for (final Map.Entry<View, ReplaySubject<Action2<View, ViewPropertyAnimator>>> entry : subjects.entrySet()) {
            final ViewPropertyAnimator animator = entry.getKey().animate();
            entry.getValue().subscribe(new Subscriber<Action2<View, ViewPropertyAnimator>>() {
                @Override
                public void onCompleted() {
                    animator.setListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            postAnimationSubject.onCompleted();
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {
                            postAnimationSubject.onCompleted();
                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(Action2<View, ViewPropertyAnimator> action) {
                    action.call(entry.getKey(), animator);
                }
            });
            entry.getValue().onCompleted();
        }
    }

    private ReplaySubject<Action2<View, ViewPropertyAnimator>> getAnimationBuildSubject(View v) {
        ReplaySubject<Action2<View, ViewPropertyAnimator>> replaySubject = subjects.get(v);
        if (replaySubject == null) {
            replaySubject = ReplaySubject.create();
            subjects.put(v, replaySubject);
        }
        return replaySubject;
    }

    private AnimateConstruction addAnimationAction(Action2<View, ViewPropertyAnimator> action, View... views) {
        for (View v : views) {
            getAnimationBuildSubject(v).onNext(action);
        }
        return this;
    }

    private AnimateConstruction addPreAnimationAction(final Action1<View[]> action, final View[] views) {
        preAnimationSubject.doOnCompleted(new Action0() {
            @Override
            public void call() {
                action.call(views);
            }
        }).subscribe();
        return this;
    }

    private AnimateConstruction addPostAnimationAction(final Action1<View[]> action, final View[] views) {
        postAnimationSubject.doOnCompleted(new Action0() {
            @Override
            public void call() {
                action.call(views);
            }
        }).subscribe();
        return this;
    }
}
