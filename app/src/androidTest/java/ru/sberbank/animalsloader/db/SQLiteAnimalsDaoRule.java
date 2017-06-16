package ru.sberbank.animalsloader.db;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.rules.ExternalResource;


public class SQLiteAnimalsDaoRule extends ExternalResource {

    private SQLLiteAnimalsDao mSQLLiteAnimalsDao;
    private static final String TEST_NAME = "test.db";

    @Override
    protected void before() throws Throwable {
        super.before();
        Context context = InstrumentationRegistry.getTargetContext();
        mSQLLiteAnimalsDao = new SQLLiteAnimalsDao(context, TEST_NAME, SQLLiteAnimalsDao.CURRENT_VERSION);
    }

    @Override
    protected void after() {
        super.after();
        mSQLLiteAnimalsDao.close();
        Context context = InstrumentationRegistry.getTargetContext();
        context.deleteDatabase(TEST_NAME);
    }

    public SQLLiteAnimalsDao getSQLLiteAnimalsDao() {
        return mSQLLiteAnimalsDao;
    }
}
