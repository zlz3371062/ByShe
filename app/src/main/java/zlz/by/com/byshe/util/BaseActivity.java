package zlz.by.com.byshe.util;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import zlz.by.com.byshe.R;
import zlz.by.com.byshe.model.ActivtyColl;
import zlz.by.com.byshe.model.PingMu;

/**
 * Created by mac on 16/4/15.
 */
public class BaseActivity  extends Activity{
    private LinearLayout navleftll , navll;
    private ImageView navbackimg;
    SystemBarTintManager ZLZtintManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivtyColl.AddActivty(this);
        ZLZtintManager =  new SystemBarTintManager(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);//true 不透明
            ZLZtintManager = new SystemBarTintManager(this);
            ZLZtintManager.setStatusBarTintEnabled(true);
//            ZLZtintManager.setStatusBarTintResource(R.color.colorAccent);//通知栏所需颜色
        }



    }

    protected  void ZTLcolor(int color){
        ZLZtintManager.setStatusBarTintResource(color);
    }

    protected  void Navcolor(int color){
        LayoutInflater apptitlein =  LayoutInflater.from(this);
        View view  = apptitlein.inflate(R.layout.nav, null);
        navleftll = (LinearLayout) findViewById(R.id.navleftimgll);
        navll = (LinearLayout) findViewById(R.id.navll);
        navll.setBackgroundColor(color);
        navbackimg = new ImageView(BaseActivity.this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(PingMu.width/25, PingMu.height/30);
        navbackimg.setImageResource(R.drawable.nav_arrow);
        navleftll.addView(navbackimg, layoutParams);
        navleftll.setOnClickListener(new myonclick());

    }

    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
//    protected void navback() {
//        navbackimg = new ImageView(BaseActivity.this);
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(PingMu.width/25, PingMu.height/30);
//        navbackimg.setImageResource(R.drawable.nav_arrow);
//        navleftll.addView(navbackimg, layoutParams);
//        navleftll.setOnClickListener(new myonclick());
//
//
//    }
    private  class myonclick implements  View.OnClickListener {


        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.navleftimgll:
                    finish();
                    break;
            }
        }
    }


    }