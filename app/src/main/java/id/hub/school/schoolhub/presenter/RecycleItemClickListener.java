package id.hub.school.schoolhub.presenter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;

public class RecycleItemClickListener implements RecyclerView.OnItemTouchListener {

    protected OnItemClickListener listener;

    @Nullable private View childView;

    private int childViewPosition;

    private GestureDetector gestureDetector;

    public RecycleItemClickListener(Context context, OnItemClickListener listener) {
        this.gestureDetector = new GestureDetector(context, new GestureListener());
        this.listener = listener;
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        childView = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
        childViewPosition = recyclerView.getChildPosition(childView);
        return childView != null && gestureDetector.onTouchEvent(motionEvent);
    }

    @Override
    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {}

    public interface OnItemClickListener {
        void onItemClick(View childView, int position);
    }

    public static abstract class SimpleOnItemClickListener implements OnItemClickListener{
        public void onItemClick(View childView, int position) {}
    }

    protected class GestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapUp(MotionEvent event) {
            if (childView != null) {
                listener.onItemClick(childView, childViewPosition);
            }

            return true;
        }

        @Override
        public boolean onDown(MotionEvent event) {
            // Best practice to always return true here.
            // http://developer.android.com/training/gestures/detector.html#detect
            return true;
        }

    }

}
