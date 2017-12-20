package ocelot.app.animator;

import android.animation.Animator;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;

import ocelot.app.R;

/**
 * Created by Jose on 1/10/2017.
 */

public class AnimatorView {

    private CoordinatorLayout rootLayout;

    public AnimatorView(CoordinatorLayout rootLayout) {
        this.rootLayout = rootLayout;
    }

    public void animator(final int CoordenadaX, final int CoordenadaY) {
        this.rootLayout.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                v.removeOnLayoutChangeListener(this);
                animator_do_it(CoordenadaX, CoordenadaY);
            }
        });
    }

    private void animator_do_it(int cx, int cy) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            float finalRadius = Math.max(rootLayout.getWidth(), rootLayout.getHeight());

            // create the animator for this view (the start radius is zero)
            Animator circularReveal = ViewAnimationUtils.createCircularReveal(rootLayout, cx, cy, 0, finalRadius);
            circularReveal.setInterpolator(new AccelerateDecelerateInterpolator());
            circularReveal.setDuration(1000);

            // make the view visible and start the animation
            circularReveal.start();
        }
    }
}
