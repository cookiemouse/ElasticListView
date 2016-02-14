package com.mouse.cookie.lib;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by cookie on 2016/2/3.
 */
public class ElasticListView extends ListView implements AbsListView.OnScrollListener {

    private int offsetY = 0;
    private float oldY = 0;

    private int firstVisibleItem;
    private int visibleItemCount, totalItemCount;

    private int heightHead, heightFoot;

    private int speed = 10;

    private Context context;

    private View headView, footView;

    public ElasticListView(Context context) {
        super(context);
        this.context = context;
        initialize();
    }

    public ElasticListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initialize();
    }

    public ElasticListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initialize();
    }

    //添加HeadView
    private void initialize() {

        setOnScrollListener(this);

        headView = new View(context);
        headView.setBackgroundColor(0x00000000);
        headView.setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT, 0));

        this.addHeaderView(headView);

        footView = new View(context);
        footView.setBackgroundColor(0x00000000);
        footView.setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT, 0));

        this.addFooterView(footView);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {

            //滑动事件
            case MotionEvent.ACTION_MOVE: {

                if (0 == firstVisibleItem && 0 == oldY) {
                    oldY = ev.getY();
                    Log.e("Tag_ELV", "oldY-->" + oldY);
                } else if (firstVisibleItem + visibleItemCount == totalItemCount && 0 == oldY) {
                    oldY = ev.getY();
                    Log.e("Tag_ELV", "oldY-->" + oldY);
                }

                if (0 != oldY) {
                    offsetY = (int) (ev.getY() - oldY);
                    headView.setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT, getHeadHeight
                            (offsetY)));
                    offsetY = (int) (oldY - ev.getY());
                    footView.setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT, getHeadHeight
                            (offsetY)));
                    Log.e("Tag_ELV", "offsetY-->" + offsetY);
                }

                break;
            }
        }

        return super.onTouchEvent(ev);
    }

    //计算下拉高度
    private int getHeadHeight(int offsetY) {
//        int max = 600;
//        return (offsetY - offsetY * offsetY / max);

        return (int) (0.4 * offsetY);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

        if (SCROLL_STATE_IDLE == scrollState) {

            oldY = 0;
            speed = 10;//重置回弹速度

            heightHead = headView.getHeight();
            heightFoot = footView.getHeight();

            final Handler handler = new Handler();
            if (heightHead > 0) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        heightHead -= speed;
                        headView.setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT,
                                heightHead));
                        if (heightHead > 0) {
                            speed += 2;
                            handler.post(this);
                        }
                    }
                });
            }

            if (heightFoot > 0) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        heightFoot -= speed;
                        footView.setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT,
                                heightFoot));
                        if (heightFoot > 0) {
                            speed += 2;
                            handler.post(this);
                        }
                    }
                });
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.firstVisibleItem = firstVisibleItem;
        this.visibleItemCount = visibleItemCount;
        this.totalItemCount = totalItemCount;
    }
}
