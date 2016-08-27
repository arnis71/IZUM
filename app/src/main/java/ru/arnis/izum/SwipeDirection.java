package ru.arnis.izum;

import android.content.ContentValues;
import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ViewAnimator;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by arnis on 24/08/16.
 */
public class SwipeDirection extends GestureDetector.SimpleOnGestureListener {

    private ViewAnimator viewAnimator;
    private Integer random;
    private Random rnd;
    private Context context;
    private boolean defaultAnim;

    public SwipeDirection(Context context,ViewAnimator viewAnimator, Integer random,boolean defaultAnim) {
        this.defaultAnim=defaultAnim;
        this.context=context;
        this.viewAnimator = viewAnimator;
        this.random=random;
        if (this.random!=null)
            rnd = new Random();

    }


    //определяем направление свайпа
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (e1.getX()<e2.getX()){ //свайп вправо
            if (defaultAnim){
                viewAnimator.setInAnimation(context,R.anim.translate_in_right);
                viewAnimator.setOutAnimation(context,R.anim.translate_out_right);
            }

            if (random==null){
                viewAnimator.showPrevious();
            }
            else {
                viewAnimator.setDisplayedChild(rnd.nextInt(random));
            }
            return true;
        } else { //свайп влево
            if (defaultAnim){
                viewAnimator.setInAnimation(context,R.anim.translate_in_left);
                viewAnimator.setOutAnimation(context,R.anim.translate_out_left);
            }
            if (random==null){

                viewAnimator.showNext();
            }
            else viewAnimator.setDisplayedChild(rnd.nextInt(random));
            return true;
        }
    }
}
