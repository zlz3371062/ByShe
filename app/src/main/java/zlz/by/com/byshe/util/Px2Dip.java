package zlz.by.com.byshe.util;

import android.content.Context;

/**
 * Created by mac on 16/4/15.
 */
public class Px2Dip {


    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    //转dp
    private  int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
