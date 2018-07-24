package com.ectechgroup.eveconcept;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;
import java.util.Map;

public class MultiDatabaseHelper {
    protected Map<DatabaseDescriptor, DatabaseOpenHelper> dbConnections = new HashMap<DatabaseDescriptor, DatabaseOpenHelper>();

    public synchronized DatabaseOpenHelper getConnection(Context context, DatabaseDescriptor desc) {
        DatabaseOpenHelper helper = dbConnections.get(desc);
        if(helper == null) {
            helper = new DatabaseOpenHelper(context, desc);
        }
        return helper;
    }
}
