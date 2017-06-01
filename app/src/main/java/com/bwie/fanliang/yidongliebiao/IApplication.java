package com.bwie.fanliang.yidongliebiao;

import android.app.Application;

import org.xutils.DbManager;
import org.xutils.x;


/**
 * Created by haozi on 2017/5/19.
 */

public class IApplication extends Application {
    private static IApplication mAppApplication;
    private SQLHelper sqlHelper;
    @Override
    public void onCreate() {
        super.onCreate();

        x.Ext.init(this);
        mAppApplication = this;

        getDaoConfig();
        getSQLHelper();
    }


    private static DbManager.DaoConfig daoConfig;
    public static DbManager.DaoConfig getDaoConfig(){
        if(daoConfig==null){
            daoConfig=new DbManager.DaoConfig()
                    .setDbVersion(1)
                    .setDbName("tt")//设置数据库的名字
                    .setAllowTransaction(true)
                    .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                        @Override
                        public void onUpgrade(DbManager db, int oldVersion, int newVersion) {

                        }
                    });
        }
        return daoConfig;
    }

    /** 获取Application */
    public static IApplication getApp() {
        return mAppApplication;
    }
    /** 获取数据库Helper */
    public SQLHelper getSQLHelper() {
        if (sqlHelper == null)
            sqlHelper = new SQLHelper(mAppApplication);
        return sqlHelper;
    }


    /** 摧毁应用进程时候调用 */
    public void onTerminate() {
        if (sqlHelper != null)
            sqlHelper.close();
        super.onTerminate();

    }
    public void clearAppCache() {
    }


}
