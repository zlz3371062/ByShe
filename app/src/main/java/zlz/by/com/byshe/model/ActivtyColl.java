package zlz.by.com.byshe.model;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 16/4/15.
 */
public class ActivtyColl {
     public static List<Activity> Activitylist  =  new ArrayList<Activity>();
     public  static  void AddActivty(Activity activity){
         Activitylist.add(activity);
      }
    public  static  void RemoveActivty(Activity activity){
         Activitylist.remove(activity);
    }
    public static void FinishAll(){

     for(Activity  activity : Activitylist){
               if(!activity.isFinishing())
               {
                   activity.finish();


               }


        }

    }

}
