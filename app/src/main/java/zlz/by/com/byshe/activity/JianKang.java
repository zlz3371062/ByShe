package zlz.by.com.byshe.activity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import zlz.by.com.byshe.R;
import zlz.by.com.byshe.util.BaseActivity;
import zlz.by.com.byshe.util.Connect;

/**
 * Created by mac on 16/4/19.
 */
public class JianKang extends BaseActivity{
    private LinearLayout ll;
    private String url = "http://japi.juhe.cn/health_knowledge/categoryList?";
    private String key = "912af47d4dd6995150527f369b893c12";
    private Connect connect = new Connect();
    private List<Integer>  list = new ArrayList<Integer>();
    private String url1 = "http://japi.juhe.cn/health_knowledge/infoList?key=912af47d4dd6995150527f369b893c12";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jiankang);
        ll = (LinearLayout) findViewById(R.id.jiankang);
        getthings();
    }

    private  void getthings(){
        new Thread(new Runnable() {
            @Override
            public void run() {
              final  String json = connect.httpgetjson(url,null,"GET","key=" + key);
                Log.e("zlz", json + "ss");
                myha.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                             JSONObject jso = new JSONObject(json);
                            Log.e("zlz",json);
                             int code = jso.getInt("error_code");
                             Log.e("zlz",code + "");
                             if(code == 0)
                             {
                                JSONArray jsa = jso.getJSONArray("result");
                                 String ssss = jso.getString("result");
                                 Log.e("zlz",ssss);
                                for (int i = 0; i < jsa.length();i++)
                                {
                                    JSONObject JS = jsa.getJSONObject(i);
                                    String  st = JS.getString("name");
                                    int id = JS.getInt("id");
                                    Log.e("zlz",st);
                                    TextView tx = new TextView(JianKang.this);
                                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                                    , ViewGroup.LayoutParams.WRAP_CONTENT);
                                    tx.setGravity(Gravity.CENTER);
                                    tx.setText(st);
                                    tx.setLayoutParams(params);
                                    ll.addView(tx);
                                    list.add(id);

                                     tx.setOnClickListener(new onclick(i));
                                }



                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });


            }
        }).start();
    }

private Handler myha = new Handler(){

};
private class  onclick implements View.OnClickListener{
    private int num;

    public onclick(int num){

    this.num = num;
    }
    @Override
    public void onClick(View v) {
       for(int i = 0; i < list.size();i++)
       {    Log.e("zlz", i + "");
           if(i == num)
           {
              new Thread(new Runnable() {
                  @Override
                  public void run() {
                         int id = list.get(num);
                          try {
                              URL url = new URL(url1);
                              Log.e("zlz", url1);
                              HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                              conn.setRequestMethod("POST");
                              BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
                              String str = "id=" + id;
                              Log.e("zlz",str);
                              bw.write(str);
                              bw.flush();
                              BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                              StringBuilder sb =new StringBuilder();
                              String line = "";
                              while ((line = br.readLine()) != null)
                              {
                                  sb.append(line);
                                  Log.e("zlz",line);
                              }

                          } catch (IOException e) {
                              e.printStackTrace();
                          }



                  }
              }).start();


               break;
           }

       }
    }
}
}
