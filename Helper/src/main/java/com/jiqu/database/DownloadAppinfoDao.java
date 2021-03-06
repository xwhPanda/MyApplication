package com.jiqu.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DOWNLOAD_APPINFO".
*/
public class DownloadAppinfoDao extends AbstractDao<DownloadAppinfo, String> {

    public static final String TABLENAME = "DOWNLOAD_APPINFO";

    /**
     * Properties of entity DownloadAppinfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property PackageName = new Property(0, String.class, "packageName", false, "PACKAGE_NAME");
        public final static Property AppName = new Property(1, String.class, "appName", false, "APP_NAME");
        public final static Property VersionCode = new Property(2, String.class, "versionCode", false, "VERSION_CODE");
        public final static Property VersionName = new Property(3, String.class, "versionName", false, "VERSION_NAME");
        public final static Property Id = new Property(4, String.class, "id", true, "ID");
        public final static Property CurrentSize = new Property(5, Long.class, "currentSize", false, "CURRENT_SIZE");
        public final static Property AppSize = new Property(6, String.class, "appSize", false, "APP_SIZE");
        public final static Property DownloadState = new Property(7, int.class, "downloadState", false, "DOWNLOAD_STATE");
        public final static Property Url = new Property(8, String.class, "url", false, "URL");
        public final static Property IconUrl = new Property(9, String.class, "iconUrl", false, "ICON_URL");
        public final static Property ApkPath = new Property(10, String.class, "apkPath", false, "APK_PATH");
        public final static Property ZipPath = new Property(11, String.class, "zipPath", false, "ZIP_PATH");
        public final static Property UnzipPath = new Property(12, String.class, "unzipPath", false, "UNZIP_PATH");
        public final static Property HasFinished = new Property(13, Boolean.class, "hasFinished", false, "HAS_FINISHED");
        public final static Property Des = new Property(14, String.class, "des", false, "DES");
        public final static Property Score = new Property(15, String.class, "score", false, "SCORE");
        public final static Property Progress = new Property(16, Float.class, "progress", false, "PROGRESS");
        public final static Property IconByte = new Property(17, byte[].class, "iconByte", false, "ICON_BYTE");
        public final static Property IsZip = new Property(18, Boolean.class, "isZip", false, "IS_ZIP");
        public final static Property Thread1 = new Property(19, Long.class, "thread1", false, "THREAD1");
        public final static Property Thread2 = new Property(20, Long.class, "thread2", false, "THREAD2");
        public final static Property Thread3 = new Property(21, Long.class, "thread3", false, "THREAD3");
        public final static Property Thread4 = new Property(22, Long.class, "thread4", false, "THREAD4");
        public final static Property Thread5 = new Property(23, Long.class, "thread5", false, "THREAD5");
    };


    public DownloadAppinfoDao(DaoConfig config) {
        super(config);
    }
    
    public DownloadAppinfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DOWNLOAD_APPINFO\" (" + //
                "\"PACKAGE_NAME\" TEXT NOT NULL ," + // 0: packageName
                "\"APP_NAME\" TEXT NOT NULL ," + // 1: appName
                "\"VERSION_CODE\" TEXT," + // 2: versionCode
                "\"VERSION_NAME\" TEXT," + // 3: versionName
                "\"ID\" TEXT PRIMARY KEY NOT NULL ," + // 4: id
                "\"CURRENT_SIZE\" INTEGER," + // 5: currentSize
                "\"APP_SIZE\" TEXT NOT NULL ," + // 6: appSize
                "\"DOWNLOAD_STATE\" INTEGER NOT NULL ," + // 7: downloadState
                "\"URL\" TEXT NOT NULL ," + // 8: url
                "\"ICON_URL\" TEXT," + // 9: iconUrl
                "\"APK_PATH\" TEXT," + // 10: apkPath
                "\"ZIP_PATH\" TEXT," + // 11: zipPath
                "\"UNZIP_PATH\" TEXT," + // 12: unzipPath
                "\"HAS_FINISHED\" INTEGER," + // 13: hasFinished
                "\"DES\" TEXT," + // 14: des
                "\"SCORE\" TEXT," + // 15: score
                "\"PROGRESS\" REAL," + // 16: progress
                "\"ICON_BYTE\" BLOB," + // 17: iconByte
                "\"IS_ZIP\" INTEGER," + // 18: isZip
                "\"THREAD1\" INTEGER," + // 19: thread1
                "\"THREAD2\" INTEGER," + // 20: thread2
                "\"THREAD3\" INTEGER," + // 21: thread3
                "\"THREAD4\" INTEGER," + // 22: thread4
                "\"THREAD5\" INTEGER);"); // 23: thread5
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DOWNLOAD_APPINFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, DownloadAppinfo entity) {
        stmt.clearBindings();
        stmt.bindString(1, entity.getPackageName());
        stmt.bindString(2, entity.getAppName());
 
        String versionCode = entity.getVersionCode();
        if (versionCode != null) {
            stmt.bindString(3, versionCode);
        }
 
        String versionName = entity.getVersionName();
        if (versionName != null) {
            stmt.bindString(4, versionName);
        }
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(5, id);
        }
 
        Long currentSize = entity.getCurrentSize();
        if (currentSize != null) {
            stmt.bindLong(6, currentSize);
        }
        stmt.bindString(7, entity.getAppSize());
        stmt.bindLong(8, entity.getDownloadState());
        stmt.bindString(9, entity.getUrl());
 
        String iconUrl = entity.getIconUrl();
        if (iconUrl != null) {
            stmt.bindString(10, iconUrl);
        }
 
        String apkPath = entity.getApkPath();
        if (apkPath != null) {
            stmt.bindString(11, apkPath);
        }
 
        String zipPath = entity.getZipPath();
        if (zipPath != null) {
            stmt.bindString(12, zipPath);
        }
 
        String unzipPath = entity.getUnzipPath();
        if (unzipPath != null) {
            stmt.bindString(13, unzipPath);
        }
 
        Boolean hasFinished = entity.getHasFinished();
        if (hasFinished != null) {
            stmt.bindLong(14, hasFinished ? 1L: 0L);
        }
 
        String des = entity.getDes();
        if (des != null) {
            stmt.bindString(15, des);
        }
 
        String score = entity.getScore();
        if (score != null) {
            stmt.bindString(16, score);
        }
 
        Float progress = entity.getProgress();
        if (progress != null) {
            stmt.bindDouble(17, progress);
        }
 
        byte[] iconByte = entity.getIconByte();
        if (iconByte != null) {
            stmt.bindBlob(18, iconByte);
        }
 
        Boolean isZip = entity.getIsZip();
        if (isZip != null) {
            stmt.bindLong(19, isZip ? 1L: 0L);
        }
 
        Long thread1 = entity.getThread1();
        if (thread1 != null) {
            stmt.bindLong(20, thread1);
        }
 
        Long thread2 = entity.getThread2();
        if (thread2 != null) {
            stmt.bindLong(21, thread2);
        }
 
        Long thread3 = entity.getThread3();
        if (thread3 != null) {
            stmt.bindLong(22, thread3);
        }
 
        Long thread4 = entity.getThread4();
        if (thread4 != null) {
            stmt.bindLong(23, thread4);
        }
 
        Long thread5 = entity.getThread5();
        if (thread5 != null) {
            stmt.bindLong(24, thread5);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, DownloadAppinfo entity) {
        stmt.clearBindings();
        stmt.bindString(1, entity.getPackageName());
        stmt.bindString(2, entity.getAppName());
 
        String versionCode = entity.getVersionCode();
        if (versionCode != null) {
            stmt.bindString(3, versionCode);
        }
 
        String versionName = entity.getVersionName();
        if (versionName != null) {
            stmt.bindString(4, versionName);
        }
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(5, id);
        }
 
        Long currentSize = entity.getCurrentSize();
        if (currentSize != null) {
            stmt.bindLong(6, currentSize);
        }
        stmt.bindString(7, entity.getAppSize());
        stmt.bindLong(8, entity.getDownloadState());
        stmt.bindString(9, entity.getUrl());
 
        String iconUrl = entity.getIconUrl();
        if (iconUrl != null) {
            stmt.bindString(10, iconUrl);
        }
 
        String apkPath = entity.getApkPath();
        if (apkPath != null) {
            stmt.bindString(11, apkPath);
        }
 
        String zipPath = entity.getZipPath();
        if (zipPath != null) {
            stmt.bindString(12, zipPath);
        }
 
        String unzipPath = entity.getUnzipPath();
        if (unzipPath != null) {
            stmt.bindString(13, unzipPath);
        }
 
        Boolean hasFinished = entity.getHasFinished();
        if (hasFinished != null) {
            stmt.bindLong(14, hasFinished ? 1L: 0L);
        }
 
        String des = entity.getDes();
        if (des != null) {
            stmt.bindString(15, des);
        }
 
        String score = entity.getScore();
        if (score != null) {
            stmt.bindString(16, score);
        }
 
        Float progress = entity.getProgress();
        if (progress != null) {
            stmt.bindDouble(17, progress);
        }
 
        byte[] iconByte = entity.getIconByte();
        if (iconByte != null) {
            stmt.bindBlob(18, iconByte);
        }
 
        Boolean isZip = entity.getIsZip();
        if (isZip != null) {
            stmt.bindLong(19, isZip ? 1L: 0L);
        }
 
        Long thread1 = entity.getThread1();
        if (thread1 != null) {
            stmt.bindLong(20, thread1);
        }
 
        Long thread2 = entity.getThread2();
        if (thread2 != null) {
            stmt.bindLong(21, thread2);
        }
 
        Long thread3 = entity.getThread3();
        if (thread3 != null) {
            stmt.bindLong(22, thread3);
        }
 
        Long thread4 = entity.getThread4();
        if (thread4 != null) {
            stmt.bindLong(23, thread4);
        }
 
        Long thread5 = entity.getThread5();
        if (thread5 != null) {
            stmt.bindLong(24, thread5);
        }
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4);
    }    

    @Override
    public DownloadAppinfo readEntity(Cursor cursor, int offset) {
        DownloadAppinfo entity = new DownloadAppinfo( //
            cursor.getString(offset + 0), // packageName
            cursor.getString(offset + 1), // appName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // versionCode
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // versionName
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // id
            cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5), // currentSize
            cursor.getString(offset + 6), // appSize
            cursor.getInt(offset + 7), // downloadState
            cursor.getString(offset + 8), // url
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // iconUrl
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // apkPath
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // zipPath
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // unzipPath
            cursor.isNull(offset + 13) ? null : cursor.getShort(offset + 13) != 0, // hasFinished
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // des
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // score
            cursor.isNull(offset + 16) ? null : cursor.getFloat(offset + 16), // progress
            cursor.isNull(offset + 17) ? null : cursor.getBlob(offset + 17), // iconByte
            cursor.isNull(offset + 18) ? null : cursor.getShort(offset + 18) != 0, // isZip
            cursor.isNull(offset + 19) ? null : cursor.getLong(offset + 19), // thread1
            cursor.isNull(offset + 20) ? null : cursor.getLong(offset + 20), // thread2
            cursor.isNull(offset + 21) ? null : cursor.getLong(offset + 21), // thread3
            cursor.isNull(offset + 22) ? null : cursor.getLong(offset + 22), // thread4
            cursor.isNull(offset + 23) ? null : cursor.getLong(offset + 23) // thread5
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, DownloadAppinfo entity, int offset) {
        entity.setPackageName(cursor.getString(offset + 0));
        entity.setAppName(cursor.getString(offset + 1));
        entity.setVersionCode(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setVersionName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setId(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setCurrentSize(cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5));
        entity.setAppSize(cursor.getString(offset + 6));
        entity.setDownloadState(cursor.getInt(offset + 7));
        entity.setUrl(cursor.getString(offset + 8));
        entity.setIconUrl(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setApkPath(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setZipPath(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setUnzipPath(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setHasFinished(cursor.isNull(offset + 13) ? null : cursor.getShort(offset + 13) != 0);
        entity.setDes(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setScore(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setProgress(cursor.isNull(offset + 16) ? null : cursor.getFloat(offset + 16));
        entity.setIconByte(cursor.isNull(offset + 17) ? null : cursor.getBlob(offset + 17));
        entity.setIsZip(cursor.isNull(offset + 18) ? null : cursor.getShort(offset + 18) != 0);
        entity.setThread1(cursor.isNull(offset + 19) ? null : cursor.getLong(offset + 19));
        entity.setThread2(cursor.isNull(offset + 20) ? null : cursor.getLong(offset + 20));
        entity.setThread3(cursor.isNull(offset + 21) ? null : cursor.getLong(offset + 21));
        entity.setThread4(cursor.isNull(offset + 22) ? null : cursor.getLong(offset + 22));
        entity.setThread5(cursor.isNull(offset + 23) ? null : cursor.getLong(offset + 23));
     }
    
    @Override
    protected final String updateKeyAfterInsert(DownloadAppinfo entity, long rowId) {
        return entity.getId();
    }
    
    @Override
    public String getKey(DownloadAppinfo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
