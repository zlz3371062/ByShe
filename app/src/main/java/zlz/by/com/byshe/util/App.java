package zlz.by.com.byshe.util;

import android.app.Application;

import zlz.by.com.byshe.R;
import zlz.by.com.byshe.model.PingMu;

/**
 * Created by mac on 16/4/15.
 */
public class App  extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        PingMu.systembarheight = getStatusBarHeight();//保存系统状态栏高度



    }




    //系统状态栏高度
    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }




}
