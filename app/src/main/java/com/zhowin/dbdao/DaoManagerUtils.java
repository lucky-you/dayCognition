package com.zhowin.dbdao;


import android.content.Context;
import android.util.Log;

import com.zhowin.daycognition.HistoryListDao;
import com.zhowin.daycognition.model.HistoryList;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * author      : Z_B
 * date       : 2018/9/6
 * function  : 操作数据库的帮助类
 */
public class DaoManagerUtils {


    /**
     * 存储信息
     */
    public static void insertHistoryListData(HistoryList HistoryList) {
        DBManager.getInstance().getHistoryDao().insert(HistoryList);
    }

    /**
     * 删除数据
     */
    public static void deleteData(HistoryList HistoryList) {
        DBManager.getInstance().getHistoryDao().delete(HistoryList);
    }

    /**
     * 根据id删除数据至数据库
     */
    public static void deleteByKeyData(Long id) {
        DBManager.getInstance().getHistoryDao().deleteByKey(id);
    }

    /**
     * 删除全部数据
     */
    public static void deleteAllData(Context context) {
        DBManager.getInstance().getHistoryDao().deleteAll();
    }

    /**
     * 更新数据库
     */
    public static void updateData(HistoryList HistoryList) {
        DBManager.getInstance().getHistoryDao().update(HistoryList);
    }

    /**
     * 插入数据
     */
    public static void insertOrReplaceData(HistoryList HistoryList) {
        DBManager.getInstance().getHistoryDao().insertOrReplace(HistoryList);
    }

    /**
     * 查询所有数据
     */
    public static List<HistoryList> queryAllHistoryData() {
        QueryBuilder<HistoryList> builder = DBManager.getInstance().getHistoryDao().queryBuilder().orderDesc(HistoryListDao.Properties.CreateTime);
        return builder.build().list();
    }

    /**
     * 插入内容
     *
     * @param key  关键内容
     * @param time 创建时间
     */
    public static void insertHistoryDao(String keyText, String time) {
        HistoryList history = new HistoryList(keyText, time);
        HistoryListDao historyDao = DBManager.getInstance().getHistoryDao();
        List<HistoryList> list = historyDao.queryBuilder().where(HistoryListDao.Properties.CreateTime.eq(time)).list();
        if (list.size() > 0) {// 时间去重
            historyDao.delete(list.get(0));
        }
        insertHistoryListData(history);
    }
}
