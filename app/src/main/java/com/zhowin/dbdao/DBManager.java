package com.zhowin.dbdao;


import com.zhowin.daycognition.DaoMaster;
import com.zhowin.daycognition.DaoSession;
import com.zhowin.daycognition.HistoryListDao;
import com.zhowin.daycognition.base.BaseApplication;

public class DBManager {

    private static final String DB_NAME = "day_cognition.db";
    private static DBManager instance;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    public static void initDao() {
        MySQLiteOpenHelper devOpenHelper = new MySQLiteOpenHelper(BaseApplication.getInstance(), DB_NAME, null);
        daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase()); //可写数据库
        daoSession = daoMaster.newSession();
    }

    public static DBManager getInstance() {
        if (instance == null) {
            synchronized (DBManager.class) {
                if (instance == null) {
                    instance = new DBManager();
                }
            }
        }
        return instance;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public HistoryListDao getHistoryDao() {
        return getDaoSession().getHistoryListDao();
    }


}
