package com.jiqu.helper.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiqu.helper.R;
import com.jiqu.helper.data.InstalledApp;
import com.jiqu.helper.holders.BaseHolder;
import com.jiqu.helper.holders.UninstallHolder;
import com.jiqu.helper.tools.InstalledAppTool;
import com.jiqu.helper.tools.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiongweihua on 2016/8/16.
 */
public class UninstallAdapter extends BaseAdapter {
    private View.OnClickListener listener;
    private Context context;
    private InstalledAppTool installedAppTool;
    private List<InstalledApp> installAppList = new ArrayList<>();

    public UninstallAdapter(Context context, int viewId, List datas) {
        super(context, viewId, datas);
        this.context = context;
        installedAppTool = new InstalledAppTool();
    }

    @Override
    public BaseHolder getHolder(View view) {
        return new UninstallHolder(view);
    }

    @Override
    public void convert(final BaseHolder baseHolder, int position) {
        final InstalledApp installedApp = (InstalledApp) mDatas.get(position);
        if (installedApp.isSystem){
            (baseHolder.getView(R.id.checkbox)).setVisibility(View.INVISIBLE);
        }
        (baseHolder.getView(R.id.icon)).setBackgroundDrawable(installedApp.appIcon);
        ((TextView)baseHolder.getView(R.id.name)).setText(installedApp.name);
        (baseHolder.getView(R.id.uninstallBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!installedApp.isSystem){
                    installedAppTool.uninstall(context,installedApp);
                    ((CheckBox)baseHolder.getView(R.id.checkbox)).setChecked(false);
                }else {
                    Tools.showToast(context,"系统应用，不能卸载");
                }
            }
        });
        ((CheckBox)baseHolder.getView(R.id.checkbox)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (installedApp.isSystem)
                    return;
                if (isChecked){
                    if (installAppList.contains(installedApp))
                        return;
                    installAppList.add(installedApp);
                }else {
                    if (installAppList.contains(installedApp))
                        installAppList.remove(installedApp);
                }
            }
        });
    }

    public List<InstalledApp> getUninstallList(){
        return installAppList;
    }

    public void setUninstallListener(View.OnClickListener listener){
        this.listener = listener;
    }
}
