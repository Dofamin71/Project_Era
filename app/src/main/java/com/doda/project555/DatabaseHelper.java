package com.doda.project555;

import android.database.SQLException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DB_PATH; // полный путь к базе данных
    private static String DB_NAME = "TestDB.db";
    private static final int SCHEMA = 1; // версия базы данных
    final static public String TABLE = "\"Test DB\""; // название таблицы в бд
    // названия столбцов
    final static public String Num1 = "_id";
    final static public String Num2 = "№*";
    final static public String Num3 = "№**";
    final static public String name = "name";
    final static public String certificate = "certificate";
    final static public String typeOfReceipt = "type of receipt";
    final static public String math = "math";
    final static public String russian = "russian";
    final static public String phys = "phys";
    final static public String essay = "essay";
    final static public String IA = "IA";
    final static public String sum = "sum";
    private Context myContext;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, SCHEMA);
        this.myContext=context;
        DB_PATH =context.getFilesDir().getPath() + DB_NAME;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
    }

    public void create_db(Context context){
        SQLiteDatabase db = context.openOrCreateDatabase("app.db", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS \"Test DB\" (\n" +
                "\"_id\"INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\"№*\"INTEGER NOT NULL,\n" +
                "\"№**\"INTEGER NOT NULL,\n" +
                "\"name\"TEXT NOT NULL,\n" +
                "\"certificate\"TEXT NOT NULL,\n" +
                "\"type of receipt\"TEXT NOT NULL,\n" +
                "\"math\"INTEGER NOT NULL,\n" +
                "\"russian\"INTEGER NOT NULL,\n" +
                "\"phys\"INTEGER NOT NULL,\n" +
                "\"essay\"INTEGER NOT NULL,\n" +
                "\"IA\"INTEGER NOT NULL,\n" +
                "\"sum\"INTEGER NOT NULL\n" +
                ");");
        //db.execSQL("INSERT INTO \"Test DB\" VALUES" +
        //      "(1,1,1,\"Сартасова Надежда Евгеньевна\",\"Копия\",\"Целевое [Б]\",78,96,58,0,0,232)");
    }

    public SQLiteDatabase open(Context context)throws SQLException {

        return SQLiteDatabase.openDatabase(String.valueOf(context.getDatabasePath("app.db")), null, SQLiteDatabase.OPEN_READWRITE);
    }
}
