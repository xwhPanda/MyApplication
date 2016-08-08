package com.jiqu.helper.tools;

import android.widget.Toast;

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

    public static void showToast(int stringId){
        Toast.makeText(HelperApplication.context,stringId,Toast.LENGTH_SHORT).show();
    }

    public static void showToast(String string){
        Toast.makeText(HelperApplication.context,string,Toast.LENGTH_SHORT).show();
    }
}
