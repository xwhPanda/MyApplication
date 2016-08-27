package com.jiqu.helper.tools;

/**
 * Created by xiongweihua on 2016/7/21.
 */
public class RequestTools {
    /** KEY **/
    public static final String Prikey = "*7&SKJuas";
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
    /** 游戏精选页广告 **/
    public static final String GAME_CHOICE_AD = "http://koa.77gamebox.com/index.php/Api/Game/getGameSelect";
    /** 游戏精选游戏 **/
    public static final String GAME_CHOICE_BASE_URL = "http://koa.77gamebox.com/index.php/Api/Game/getGameList?columnId=";
    /** 游戏分类 **/
    public static final String GAME_CLASSIFICATION = "http://koa.77gamebox.com/index.php/Api/Game/getColumn";
    /** 游戏详情 **/
    public static final String GAME_DETAIL = "http://koa.77gamebox.com/index.php/Api/InfoDetail/getGame?id=";
    /** 应用详情 **/
    public static final String APP_DETAIL = "http://koa.77gamebox.com/index.php/Api/InfoDetail/getApply?id=";
    /** 游戏排行列表 **/
    public static final String GAME_RANKING_LIST = "http://koa.77gamebox.com/index.php/Api/Game/getGameRank";
    /** 游戏排行数据 **/
    public static final String GAME_LIST = "http://koa.77gamebox.com/index.php/Api/Game/getGameList?columnId=";
    /** 应用精选 **/
    public static final String APP_CHOICE = "http://koa.77gamebox.com/index.php/Api/Apply/getApplySelect";
    /** 应用分类 **/
    public static final String APP_CLASSIFICATION = "http://koa.77gamebox.com/index.php/Api/Apply/getColumn";
    /** 应用分类 **/
    public static final String APP_RANKING = "http://koa.77gamebox.com/index.php/Api/Apply/getApply?columnId=25";
    /** 应用新锐**/
    public static final String APP_NEW_APPLICATION = "http://koa.77gamebox.com/index.php/Api/Apply/getApply?columnId=26";
    /** 我的页面 **/
    public static final String MINE_CLASSIFICATION = "http://koa.77gamebox.com/index.php/Api/My/getMyData";
    /** 我的页面，游戏推荐 **/
    public static final String MINE_GAME_RECOMMEND = "http://koa.77gamebox.com/index.php/Api/My/getGameColumn";
    /** 游戏推荐base **/
    public static final String MINE_GAME_RECOMMEND_BASE = "http://koa.77gamebox.com/index.php/Api/My/getGameByColumn?columnId=";
    /** 我的页面，应用推荐 **/
    public static final String MINE_APP_RECOMMEND = "http://koa.77gamebox.com/index.php/Api/My/getApplyColumn";
    /** 应用推荐base **/
    public static final String MINE_APP_RECOMMEND_BASE = "http://koa.77gamebox.com/index.php/Api/My/getApplyByColumn?columnId=";
    /** 我的页面，猜你喜欢 **/
    public static final String MINE_GUESS_YOU_LIKE = "http://koa.77gamebox.com/index.php/Api/My/getYourLike";
    /** 应用详情，评论 **/
    public static final String APP_DETAIL_COMMENT = "http://koa.77gamebox.com/index.php/Api/Apply/getComments?aid=";
    /** 应用详情，相关 **/
    public static final String APP_DETAIL_CORRELATION = "http://koa.77gamebox.com/index.php/Api/Apply/getRel?aid=";
    /** 搜索 **/
    public static final String SEARCH = "http://koi.77gamebox.com/index.php/Api/Recommend/getSearch";
    /** 根据内容搜索 **/
    public static final String SEARCH_DATA = "http://koi.77gamebox.com/index.php/Api/Recommend/getBySearch?search=";
    /** 用户注册 **/
    public static final String REGISTER_ACCOUNT = "http://koa.77gamebox.com/index.php/Api/User/register";
    /** 用户登录 **/
    public static final String LOGIN = "http://koa.77gamebox.com/index.php/Api/User/login";
}
