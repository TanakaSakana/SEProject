package com.example.desktop.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.desktop.project.R;


public class MainFragment extends Fragment implements GestureDetector.OnGestureListener {

    static final String TAG = "Main Fragment";
    ViewFlipper switcher;
    GestureDetector detector;
    MainActivity.MyOnTouchListener myOnTouchListener;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (android.os.Build.VERSION.SDK_INT >= 11) {
            getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED, WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        }
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_mainactivity, container,false);
        setHasOptionsMenu(true);
        switcher = (ViewFlipper) root.findViewById(R.id.switcher);
        switcher.setDrawingCacheEnabled(true);
        detector = new GestureDetector(
                getActivity(), this);
        myOnTouchListener = new MainActivity.MyOnTouchListener() {
            @Override
            public boolean onTouch(MotionEvent ev) {
                boolean result = detector.onTouchEvent(ev);
                return result;


            }
        };

        ((MainActivity) getActivity()).registerMyOnTouchListener(myOnTouchListener);

        TextView textView =(TextView)root.findViewById(R.id.textView1);
        textView.setClickable(true);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='https://hk.deals.yahoo.com/hong-kong/offer/476322'> Click here to buy the coupon </a>";
        textView.setText(Html.fromHtml(text));

        TextView textView2 =(TextView)root.findViewById(R.id.textView2);
        textView2.setClickable(true);
        textView2.setMovementMethod(LinkMovementMethod.getInstance());
        String text2 = "<a href='https://hk.deals.yahoo.com/hong-kong/offer/492820'> Click here to buy the coupon </a>";
        textView2.setText(Html.fromHtml(text2));

        TextView textView3 =(TextView)root.findViewById(R.id.textView3);
        textView3.setClickable(true);
        textView3.setMovementMethod(LinkMovementMethod.getInstance());
        String text3 = "<a href='http://www.oasistrek.com/'> Oasistrek </a>";
        textView3.setText(Html.fromHtml(text3));


        return root;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.i("Fling", "Activity onDown!");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.i("Fling", "Activity onShowPress!");

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.i("Fling", "Activity onSingleTapUp!");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.i("Fling", "Activity onScroll!");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.i("Fling", "Activity onLongPress!");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1.getY() - e2.getY() > 120) {
            switcher.setFlipInterval(0);
            switcher.setInAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.push_up_in));
            switcher.setOutAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.push_up_out));
            switcher.showNext();
            } else if (e2.getY() - e1.getY() > 120) {
            switcher.setFlipInterval(0);
            switcher.setOutAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.push_down_out));
            switcher.setInAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.push_down_in));
            switcher.showPrevious();
        }
        return false;
    }

    @Override
    public void onDetach() {
        Log.e(TAG, "onDetach");
        ((MainActivity) getActivity()).unregisterMyOnTouchListener(myOnTouchListener);
        super.onDetach();
    }
}
