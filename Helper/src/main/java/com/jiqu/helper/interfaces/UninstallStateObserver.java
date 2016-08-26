package com.jiqu.helper.interfaces;


import com.jiqu.helper.data.InstalledApp;

public interface UninstallStateObserver {
	public void onUninstallStateChanged(InstalledApp app);
}
