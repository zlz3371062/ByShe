package zlz.by.com.byshe.activity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import zlz.by.com.byshe.R;
import zlz.by.com.byshe.model.PingMu;
import zlz.by.com.byshe.util.BaseActivity;
import zlz.by.com.byshe.util.Connect;

public class MainActivity extends BaseActivity {
    private TextView jiankang;
    private String url = "http://japi.juhe.cn/health_knowledge/categoryList?";
    private String key = "912af47d4dd6995150527f369b893c12";
    private Connect connect = new Connect();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        pingmuxy(); //保存屏幕像素信息
        super.ZTLcolor(R.color.juse);
        super.Navcolor(R.color.huise);

        jiankang = (TextView) findViewById(R.id.jiankang);
        jiankang.setOnClickListener(new onclicl());


    }


    private void pingmuxy(){
        WindowManager zlzwm = this.getWindowManager();
        Point size = new Point();
        zlzwm.getDefaultDisplay().getSize(size);
        PingMu.height = size.y;
        PingMu.width  = size.x;
    }

    private class onclicl implements  View.OnClickListener{


        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case  R.id.jiankang:
                 Intent intent = new Intent(MainActivity.this,JianKang.class);
                    startActivity(intent);
                  break;
//                case  R.id.jiankang:
//
//
//                    break;
//                case  R.id.jiankang:
//
//
//                    break;
            }



        }
    }
    private  void getthings(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String json = connect.httpgetjson(url,null,"GET","key=" + key);
            }
        }).start();



    }



}