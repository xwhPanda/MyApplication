package com.jiqu.helper.holders;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jiqu.helper.R;
import com.jiqu.helper.tools.MetricsTool;
import com.jiqu.helper.tools.UIUtil;

/**
 * Created by xiongweihua on 2016/7/7.
 */
public class RecommendAppHolder extends BaseHolder{
    public LinearLayout rankLin;
    public TextView rankText;
    public SimpleDraweeView gameIcon;
    public LinearLayout gameDetailRel;
    public LinearLayout gameNameLin;
    public TextView gameName;
    public TextView gameType;
    public ImageView gameGift;
    public LinearLayout gameSizeLin;
    public TextView downloadCountText;
    public TextView downloadCount;
    public TextView gameSizeText;
    public TextView gameSize;
    public TextView gameDetail;
    private RelativeLayout progressRel;
    private TextView downloadingTip;
    private ProgressBar downloadingBar;
    private RelativeLayout downloadRel;
    public Button download;

    public RecommendAppHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void initView(){
        rankLin = (LinearLayout) itemView.findViewById(R.id.rankLin);
        rankText = (TextView) itemView.findViewById(R.id.rankText);
        gameIcon = (SimpleDraweeView) itemView.findViewById(R.id.gameIcon);
        gameDetailRel = (LinearLayout) itemView.findViewById(R.id.gameDetailRel);
        gameNameLin = (LinearLayout) itemView.findViewById(R.id.gameNameLin);
        gameName = (TextView) itemView.findViewById(R.id.gameName);
        gameType = (TextView) itemView.findViewById(R.id.gameType);
        gameGift = (ImageView) itemView.findViewById(R.id.gameGift);
        gameSizeLin = (LinearLayout) itemView.findViewById(R.id.gameSizeLin);
        downloadCountText = (TextView) itemView.findViewById(R.id.downloadCountText);
        downloadCount = (TextView) itemView.findViewById(R.id.downloadCount);
        gameSizeText = (TextView) itemView.findViewById(R.id.gameSizeText);
        gameSize = (TextView) itemView.findViewById(R.id.gameSize);
        gameDetail = (TextView) itemView.findViewById(R.id.gameDetail);
        progressRel = (RelativeLayout) itemView.findViewById(R.id.progressRel);
        downloadingTip = (TextView) itemView.findViewById(R.id.downloadingTip);
        downloadingBar = (ProgressBar) itemView.findViewById(R.id.downloadingBar);
        downloadRel = (RelativeLayout) itemView.findViewById(R.id.downloadRel);
        download = (Button) itemView.findViewById(R.id.download);
        try {
            UIUtil.setTextSize(gameName,40);
            UIUtil.setTextSize(gameType,35);
            UIUtil.setTextSize(downloadCountText,30);
            UIUtil.setTextSize(downloadCount,30);
            UIUtil.setTextSize(gameSizeText,30);
            UIUtil.setTextSize(gameSize,30);
            UIUtil.setTextSize(gameDetail,30);
            UIUtil.setTextSize(downloadingTip,25);
            UIUtil.setTextSize(rankText,30);

            UIUtil.setViewSize(rankLin,MetricsTool.Rx * 150,MetricsTool.Rx * 54);
            UIUtil.setViewSize(download, MetricsTool.Rx * 114,MetricsTool.Rx * 90);
            UIUtil.setViewSize(gameGift,MetricsTool.Rx * 45,MetricsTool.Rx * 45);
            UIUtil.setViewSize(gameType,MetricsTool.Rx * 90,MetricsTool.Rx * 45);
            UIUtil.setViewSize(gameIcon, MetricsTool.Rx * 150,MetricsTool.Rx * 150);
            UIUtil.setViewSize(downloadingBar,MetricsTool.Rx * 110,MetricsTool.Rx * 20);

            UIUtil.setViewSizeMargin(gameType,MetricsTool.Rx * 15 ,0,0,0);
            UIUtil.setViewSizeMargin(gameGift,MetricsTool.Rx * 15 ,0,0,0);
            UIUtil.setViewSizeMargin(gameDetailRel,MetricsTool.Rx * 25 ,0,0,0);
            UIUtil.setViewSizeMargin(gameSizeLin,0,MetricsTool.Rx * 15,0,0);
            UIUtil.setViewSizeMargin(gameDetail,0,MetricsTool.Rx * 15,0,0);
            UIUtil.setViewSizeMargin(gameIcon,MetricsTool.Rx * 35,MetricsTool.Rx * 40,0,MetricsTool.Rx * 40);
            UIUtil.setViewSizeMargin(downloadRel,0,MetricsTool.Rx * 20,0,0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}