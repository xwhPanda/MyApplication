package com.jiqu.helper.tools;

import android.content.Context;
import android.widget.Toast;

import com.jiqu.helper.R;
import com.jiqu.helper.application.HelperApplication;

/**
 * Created by xiongweihua on 2016/7/6.
 */
public class Tools {

    public static int getColor(int color){
        return HelperApplication.context.getResources().getColor(color);
    }

    public static String getString(int string){
        return  HelperApplication.context.getResources().getString(string);
    }

    public static void showToast(Context context,int stringId){
        try {
            Toast.makeText(context,stringId,Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void showToast(Context context,String string){
        try{
            Toast.makeText(context,string,Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /** 0:游戏 1:应用 **/
    public static int getType(String type){
        int typeId = -1;
        if (getString(R.string.game).equals(type)){
            typeId = 0;
        }else if (getString(R.string.app).equals(type)){
            typeId = 1;
        }
        return  typeId;
    }
}