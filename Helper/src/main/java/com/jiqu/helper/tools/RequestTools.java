package com.jiqu.helper.tools;

/**
 * Created by xiongweihua on 2016/7/21.
 */
public class RequestTools {
    /** 推荐页精选 **/
    public static final String RECOMMEND_APP = "http://koa.77gamebox.com/index.php/Api/Recommend/getRecommendData";
    /** 推荐分类 **/
    public static final String RECOMMEND_CLASSIFICATION = "http://koa.77gamebox.com/index.php/Api/Recommend/getColumn";
    /** 推荐发现 **/
    public static final String RECOMMEND_FINDINGS = "http://koa.77gamebox.com/index.php/Api/Recommend/getFound";
    /** 推荐软件榜单 **/
    public static final String RECOMMEND_APP_RANK = "http://koa.77gamebox.com/index.php/Api/Recommend/getApp?columnId=63";
    /** 推荐游戏榜单 **/
    public static final String RECOMMEND_GAME_RANK = "http://koa.77gamebox.com/index.php/Api/Recommend/getApp?columnId=64";
    /** 推荐必备应用 **/
    public static final String RCOMMEND_APP_PREREQUISITES = "http://koa.77gamebox.com/index.php/Api/Recommend/getNecessarySoft";
    /** 推荐必备游戏 **/
    public static final String RECOMMEND_GAME_PREREQUISITES = "http://koa.77gamebox.com/index.php/Api/Recommend/getNecessaryGame";
}
