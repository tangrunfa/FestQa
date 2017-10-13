package com.example.tyz.latte.ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Administrator on 2017/10/11.
 */

public class DatabaseManager {

    private DaoSession mDaoSession=null;
    private UserProfileDao mDao=null;

    private  DatabaseManager(){

    }
    private void initDao(Context context){
        final ReleaseOpenHelper helper=new ReleaseOpenHelper(context,"fest_qa");
        final Database db=helper.getWritableDb();
        mDaoSession=new DaoMaster(db).newSession();
        mDao=mDaoSession.getUserProfileDao();
    }
    public DatabaseManager init(Context context){
        initDao(context);
        return this;
    }
    private static  final  class  Holder{
        private static final DatabaseManager INSTANCE=new DatabaseManager();

    }
    public static DatabaseManager getInstance(){
        return Holder.INSTANCE;
    }

    public final UserProfileDao getDao() {
        return mDao;
    }
}
