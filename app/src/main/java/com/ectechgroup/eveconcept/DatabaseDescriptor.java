package com.ectechgroup.eveconcept;

import android.database.sqlite.SQLiteDatabase;

import java.util.Objects;

public class DatabaseDescriptor {
    protected String databaseName;
    protected String tableName;
    protected String createStatement;
    protected int version;
    protected SQLiteDatabase.CursorFactory factory;

    //     private static final String DATABASE_CREATE = "create table " + DATABASE_TABLE + "( _id text primary key,name text not null,apparent_ip text not null, service_url text not null);";
    public DatabaseDescriptor() {

    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getCreateStatement() {
        return createStatement;
    }

    public void setCreateStatement(String createStatement) {
        this.createStatement = createStatement;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public SQLiteDatabase.CursorFactory getFactory() {
        return factory;
    }

    public void setFactory(SQLiteDatabase.CursorFactory factory) {
        this.factory = factory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatabaseDescriptor that = (DatabaseDescriptor) o;
        return version == that.version &&
                Objects.equals(databaseName, that.databaseName) &&
                Objects.equals(tableName, that.tableName) &&
                Objects.equals(createStatement, that.createStatement);
    }

    @Override
    public int hashCode() {

        return Objects.hash(databaseName, tableName, createStatement, version);
    }
}
