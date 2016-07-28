package com.jiqu.helper.tools;

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
}
