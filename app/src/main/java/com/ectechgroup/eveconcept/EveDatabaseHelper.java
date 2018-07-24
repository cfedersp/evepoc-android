package com.ectechgroup.eveconcept;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;
import java.util.Map;

public class EveDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "evepoc";
    public static final String DATABASE_TABLE = "current_location";
    public static final int DATABASE_VERSION = 1;
    private static final String DATABASE_CREATE = "create table " + DATABASE_TABLE + "( _id text primary key,name text,apparent_ip text, service_url text, joined_date date);";

    private static EveDatabaseHelper sInstance = null;
    private static SQLiteDatabase eveDb = null;

    public static synchronized EveDatabaseHelper getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx

        if (sInstance == null) {
            sInstance = new EveDatabaseHelper(context);
        }
        return sInstance;
    }

    public SQLiteDatabase getWritableDatabase() {
        if ((eveDb == null) || (!eveDb.isOpen())) {
            eveDb = super.getWritableDatabase();
        }

        return eveDb;
    }

    /**
     * Constructor should be private to prevent direct instantiation.
     * make call to static method "getInstance()" instead.
     */
    private EveDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // if a connection is opened with a newer version, I guess this method gets called.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onOpen(SQLiteDatabase sqLiteDatabase) {
        super.onOpen(sqLiteDatabase);
    }

    public void insertVisit(EveLocale localData) {
        SQLiteDatabase eveDb = getWritableDatabase();
        eveDb.beginTransaction();
        Object[] binds = {localData.getNetworkId(), localData.getEstablishmentId(), localData.getEstablishmentName(), localData.getNetworkId(), new Date()};
        eveDb.execSQL("insert into " + DATABASE_TABLE + " (_id, name, apparent_ip, service_url, joined_date) values (?, ?, ?, ?, ?)", binds);
        eveDb.endTransaction();
    }

    @Override
    public void close() {
        super.close();
        if (eveDb != null) {
            eveDb.close();
            eveDb = null;
        }
    }
}
