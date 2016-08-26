package com.jiqu.helper.activity;

import android.app.ActivityManager;
import android.content.pm.PackageInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jiqu.helper.BaseActivity;
import com.jiqu.helper.R;
import com.jiqu.helper.application.HelperApplication;
import com.jiqu.helper.tools.ClearTool;
import com.jiqu.helper.tools.Tools;
import com.jiqu.helper.view.TitleBar;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class ClearCacheActivity extends BaseActivity implements OnClickListener{
	private final int START_CLEAR_RUBISH = 0;
	private final int CLEAR_RUBISH_COMPLETED = 1;
	private final int START_SCAN = 2;
	private final int SCAN_COMPELTED = 3;
	private final int START_SCAN_PROGRESS = 4;
	public final int SCAN_PROCESS_COMPLETED = 5;
	private final int START_SCAN_RUBISH = 6;
	private final int SCAN_RUBISH_COMPLETE = 7;
	public final int KILL_PROCESS_COMPLETED = 8;
	private final int START_KILL_PROCESS = 9;
	private final int REFRESH_SCORE = 10;
	private List<File> files = new ArrayList<>();
	private List<ActivityManager.RunningAppProcessInfo> processInfos = new ArrayList<>();
	private boolean isCleared = false;
	private boolean runing = true;
	private boolean quit = false;
	private SQLiteDatabase db;
	private int score = 100;
	private int memoryPre = 100;
	private long beforeMemory = 0;
	private TitleBar titleBar;
	private Animation animation_1,animation_2;
	private ImageView scanBg;
	private TextView scanScore;
	private Button speedUp;
	private TextView scanTip;
	private CheckBox closeProcessBox,clearRubishBox;
	private TextView closeProcessCount,clearRubishCount;
	private ImageView closeProcessImg;
	private ImageView clearRubishImg;


	@Override
	public int getContentView() {
		// TODO Auto-generated method stub
		return R.layout.clear_cache_layout;
	}

	private void initClearDB() {
		File file = new File(HelperApplication.DATA_FILE_PATH + File.separator + "clearpath.db");
		if (!file.exists()) {
			copyFile();
		}
	}

	private void copyFile() {
		try {
			InputStream is = getAssets().open("clearpath.db");
			OutputStream fos = this.openFileOutput("clearpath.db", MODE_PRIVATE);
			byte[] buffer = new byte[1024];
			int len;
			while ((len = is.read(buffer)) != -1) {
				fos.write(buffer, 0, len);
			}
			fos.flush();
			fos.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initView(){
		initClearDB();
		titleBar = (TitleBar) findViewById(R.id.titleBar);
		animation_1 = AnimationUtils.loadAnimation(ClearCacheActivity.this, R.anim.clear_img_anim);
		animation_2 = AnimationUtils.loadAnimation(ClearCacheActivity.this, R.anim.clear_small_img_anim);
		scanBg = (ImageView) findViewById(R.id.scanBg);
		scanScore = (TextView) findViewById(R.id.scanScore);
		speedUp = (Button) findViewById(R.id.speedUp);
		scanTip = (TextView) findViewById(R.id.scanTip);
		closeProcessBox = (CheckBox) findViewById(R.id.closeProcessBox);
		clearRubishBox = (CheckBox) findViewById(R.id.clearRubishBox);
		closeProcessCount = (TextView) findViewById(R.id.closeProcessCount);
		clearRubishCount = (TextView) findViewById(R.id.clearRubishCount);
		closeProcessImg = (ImageView) findViewById(R.id.closeProcessImg);
		clearRubishImg = (ImageView) findViewById(R.id.clearRubishImg);

		speedUp.setOnClickListener(this);
		titleBar.setShareVisible(View.INVISIBLE);
		titleBar.setTitle("清理加速");
		titleBar.setActivity(this);
		titleBar.setBackgroundColor(Tools.getColor(R.color.clear_before_color));

		handler.sendEmptyMessage(START_SCAN);
	}

	Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			int what = msg.what;
			switch (what) {
				case START_KILL_PROCESS://开始清理
					scanBg.setBackgroundResource(R.mipmap.scanning);
					scanBg.startAnimation(animation_1);
					speedUp.setEnabled(false);
					scanTip.setText("正在清理");
					speedUp.setBackgroundResource(R.mipmap.unfinish);
					if (closeProcessBox.isChecked()) {
						ClearTool.getInstance().killProcess(processInfos,handler);
					}
					if(clearRubishBox.isChecked()){
						sendEmptyMessage(START_CLEAR_RUBISH);
					}
					break;

				case START_CLEAR_RUBISH:
					if (clearRubishBox.isChecked()) {
						if (files.size() > 0) {
							for (File file : files) {
								try {
									deleteFolder(file);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							sendEmptyMessage(CLEAR_RUBISH_COMPLETED);
						}else {
							sendEmptyMessage(CLEAR_RUBISH_COMPLETED);
						}
					}
					break;

				case CLEAR_RUBISH_COMPLETED://清理完毕
					isCleared = true;
					speedUp.setEnabled(true);
					scanScore.setText("100");
					stopAnimation();
					scanTip.setText("清理完成");
					speedUp.setBackgroundResource(R.mipmap.finish);
					speedUp.setTextColor(getResources().getColor(R.color.white));

					closeProcessCount.setText("清理内存" + ClearTool.getDataSize(ClearTool.getInstance().getAvailMemory(ClearCacheActivity.this) - beforeMemory));
					break;

				case KILL_PROCESS_COMPLETED:
					sendEmptyMessage(START_CLEAR_RUBISH);
					break;

				case START_SCAN://开始扫描
					new RandThread().start();
					sendEmptyMessage(START_SCAN_PROGRESS);
					speedUp.setEnabled(false);
					break;

				case SCAN_COMPELTED://扫描完毕
					runing = false;
					if (memoryPre < 70 ) {
						memoryPre = 70;
						scanScore.setText("70");
					}else {
						scanScore.setText(memoryPre + "");
					}
					closeProcessBox.setVisibility(View.VISIBLE);
					closeProcessBox.setChecked(true);
					clearRubishBox.setVisibility(View.VISIBLE);
					clearRubishBox.setChecked(true);
					closeProcessImg.setVisibility(View.INVISIBLE);
					clearRubishImg.setVisibility(View.INVISIBLE);
					scanBg.clearAnimation();
					clearRubishImg.clearAnimation();
					speedUp.setEnabled(true);

					break;

				case SCAN_PROCESS_COMPLETED://扫描后台进程完毕
					sendEmptyMessage(START_SCAN_RUBISH);
					closeProcessImg.clearAnimation();
					closeProcessCount.setText(processInfos.size() +"个可清理进程");
					break;

				case START_SCAN_PROGRESS://开始扫描后台进程
					processInfos.clear();
					scanBg.startAnimation(animation_1);
					closeProcessImg.startAnimation(animation_2);
					processInfos.addAll(ClearTool.getInstance().getProcess(HelperApplication.context, handler));
					break;

				case START_SCAN_RUBISH://开始扫描垃圾
					start();
					clearRubishImg.startAnimation(animation_2);
					break;

				case SCAN_RUBISH_COMPLETE://扫描垃圾完毕
					sendEmptyMessage(SCAN_COMPELTED);
					if (msg.obj.equals("0B") || msg.obj.equals("0KB")) {
						clearRubishCount.setText("没有垃圾");
					}else {
						clearRubishCount.setText(msg.obj + "垃圾");
					}
					break;
				case REFRESH_SCORE:
					int num = msg.arg1;
					if (memoryPre <= score) {
						score = score - num;
					}
					scanScore.setText(score + "");
					break;
			}
		}
	};

	private void stopAnimation(){
		scanBg.clearAnimation();
		closeProcessImg.clearAnimation();
		clearRubishImg.clearAnimation();
	}

	/**
	 * 开始清理缓存文件
	 *
	 * @param
	 */
	public void start() {
		db = SQLiteDatabase.openDatabase(HelperApplication.DATA_FILE_PATH + File.separator + "clearpath.db", null, SQLiteDatabase.OPEN_READONLY);
		// 获取所有的安装包信息
		new Thread() {

			@Override
			public void run() {
				List<PackageInfo> packinfos = getPackageManager().getInstalledPackages(0);
				long size = 0;
				for (PackageInfo info : packinfos) {
					String packname = info.packageName;
					Cursor cursor = db.rawQuery("select filepath from softdetail where apkname=?", new String[] { packname });
					while (db.isOpen() && !cursor.isClosed() && cursor.moveToNext()) {
						String path = cursor.getString(0);
						File file = new File(Environment.getExternalStorageDirectory(), path);
						try {
							size += getFolderSize(file);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						files.add(file);
						if (quit) {
							break;
						}
					}
					cursor.close();
					if (quit) {
						return;
					}
				}
				Message msg = Message.obtain();
				msg.what = SCAN_RUBISH_COMPLETE;
				msg.obj = ClearTool.getDataSize(size);
				handler.sendMessageDelayed(msg, 3 * 1000);
				db.close();
			}

		}.start();
	}

	public long getFolderSize(java.io.File file) throws Exception {
		long size = 0;
		java.io.File[] fileList = file.listFiles();
		if (fileList != null && fileList.length > 0) {
			for (int i = 0; i < fileList.length; i++) {
				if (fileList[i].isDirectory()) {
					size = size + getFolderSize(fileList[i]);
				} else {
					size = size + fileList[i].length();
				}
			}
		}
		return size;
	}

	private void deleteFolder(File file) throws Exception{
		java.io.File[] fileList = file.listFiles();
		for (int i = 0; i < fileList.length; i++) {
			if (fileList[i].isDirectory()) {
				deleteFolder(fileList[i]);
			} else {
				fileList[i].delete();
			}
		}
	}

	@Override
	public void initViewSize(){

	}

	@Override
	public void initData() {

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.speedUp) {
			if (isCleared) {
				finish();
			}else {
				if (!closeProcessBox.isChecked() && !clearRubishBox.isChecked()) {
					Toast.makeText(this, "请选择需要清理的内容", Toast.LENGTH_SHORT).show();
				}else {
					handler.sendEmptyMessage(START_KILL_PROCESS);
				}
			}
		}
	}
	

	protected void onDestroy() {
		super.onDestroy();
		quit = true;
		if (db != null) {
			db.close();
		}
	}

	private class RandThread extends Thread{
		@Override
		public void run() {
			while (runing) {
				try {
					int num = (int)((Math.random()) * 8);
					Message msg = handler.obtainMessage();
					msg.what = REFRESH_SCORE;
					msg.arg1 = num;
					handler.sendMessage(msg);
					sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
