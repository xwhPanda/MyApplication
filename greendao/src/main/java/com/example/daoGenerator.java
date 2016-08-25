package com.example;


import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class daoGenerator {
	public static void main(String[] args) throws Exception {
	    Schema schema = new Schema(1,"com.jiqu.database");
	    
	    addDownloadGame(schema);
	    addAccountInfo(schema);
	    addNoticeTable(schema);
	    addMessageTable(schema);
	    new DaoGenerator().generateAll(schema, "C:\\Users\\Administrator\\AndroidStudioProjects\\MyApplication\\Helper\\src\\main\\java");
	  }
	  
	  private static void addDownloadGame(Schema schema)
	  {
	    Entity downloadTable = schema.addEntity("DownloadAppinfo");
	    downloadTable.addStringProperty("packageName").notNull();//包名
	    downloadTable.addStringProperty("appName").notNull();//应用名
	    downloadTable.addStringProperty("versionCode");//版本号
	    downloadTable.addStringProperty("versionName");//版本名
	    downloadTable.addStringProperty("id").primaryKey();//id
	    downloadTable.addLongProperty("currentSize");//当前大小
	    downloadTable.addStringProperty("appSize").notNull();//总大小
	    downloadTable.addIntProperty("downloadState").notNull();//下载状态
	    downloadTable.addStringProperty("url").notNull();//下载链接
	    downloadTable.addStringProperty("iconUrl");//icon链接
	    downloadTable.addStringProperty("apkPath");//apk保存路径
	    downloadTable.addStringProperty("zipPath");//zip包存放路径
	    downloadTable.addStringProperty("unzipPath");//压缩包解压路径
	    downloadTable.addBooleanProperty("hasFinished");//是否下载完成
	    downloadTable.addStringProperty("des");//应用描述
	    downloadTable.addStringProperty("score");//评分
	    downloadTable.addFloatProperty("progress");//下载进度
	    downloadTable.addByteArrayProperty("iconByte");//应用图标
	    downloadTable.addBooleanProperty("isZip");//是否是压缩包
	    downloadTable.addLongProperty("thread1");//多线程下载完成大小
	    downloadTable.addLongProperty("thread2");//多线程下载完成大小
	    downloadTable.addLongProperty("thread3");//多线程下载完成大小
	    downloadTable.addLongProperty("thread4");//多线程下载完成大小
	    downloadTable.addLongProperty("thread5");//多线程下载完成大小
	  }
	  
	  private static void addAccountInfo(Schema schema){
		  Entity downloadTable = schema.addEntity("Account");
		  downloadTable.addStringProperty("nickname");
		  downloadTable.addStringProperty("username");
		  downloadTable.addIntProperty("gender");
		  downloadTable.addStringProperty("birthday");
		  downloadTable.addStringProperty("qq");
		  downloadTable.addStringProperty("phone");
		  downloadTable.addStringProperty("level");
		  downloadTable.addStringProperty("email");
		  downloadTable.addStringProperty("uid").primaryKey();
		  downloadTable.addStringProperty("photo");
	  }
	  
	  private static void addNoticeTable(Schema schema){
		  Entity noticeTable = schema.addEntity("NoticeTable");
		  noticeTable.addIdProperty();
		  noticeTable.addStringProperty("notice");
		  noticeTable.addStringProperty("time");
	  }
	  
	  private static void addMessageTable(Schema schema){
		  Entity messageTable = schema.addEntity("MessageTable");
		  messageTable.addIdProperty();
		  messageTable.addStringProperty("message");
		  messageTable.addStringProperty("time");
	  }
}
