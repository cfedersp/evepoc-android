package com.ectechgroup.eveconcept;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    protected DatabaseDescriptor descriptor;

    public DatabaseOpenHelper(Context context, DatabaseDescriptor descriptor) {
        super(context, descriptor.getDatabaseName(), descriptor.getFactory(), descriptor.getVersion());
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(descriptor.getCreateStatement());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // if a connection is opened with a newer version, I guess this method gets called.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + descriptor.getTableName());
        onCreate(sqLiteDatabase);
    }
}
