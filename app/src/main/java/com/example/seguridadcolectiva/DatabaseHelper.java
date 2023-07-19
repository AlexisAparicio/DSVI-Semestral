package com.example.seguridadcolectiva;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mybasedata1.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_USERNAME = "nombre";
    private static final String COLUMN_USERAPELLIDO = "apellido";
    private static final String COLUMN_PROVINCIA = "provincia";
    private static final String COLUMN_CORREGIMIENTO = "corregimiento";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_USERS + " (" +
                COLUMN_EMAIL + " TEXT PRIMARY KEY," +
                COLUMN_USERNAME + " TEXT," +
                COLUMN_USERAPELLIDO + " TEXT," +
                COLUMN_PASSWORD + " TEXT," +
                COLUMN_PROVINCIA + " TEXT," +
                COLUMN_CORREGIMIENTO + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public boolean registerUser(String email, String password, String nombre, String apellido, String provincia, String corregimiento) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Verificar si el correo electrónico ya existe en la base de datos
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_EMAIL + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email});
        if (cursor.getCount() > 0) {
            // El correo electrónico ya existe, no se puede registrar el usuario
            cursor.close();
            db.close();
            return false;
        }
        cursor.close();

        // El correo electrónico no existe, se puede registrar el usuario
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_USERNAME, nombre);
        values.put(COLUMN_USERAPELLIDO, apellido);
        values.put(COLUMN_PROVINCIA, provincia);
        values.put(COLUMN_CORREGIMIENTO, corregimiento);
        long result = db.insert(TABLE_USERS, null, values);
        db.close();
        return result != -1;
    }

    public boolean loginUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_EMAIL + " = ? AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(TABLE_USERS, null, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }
    public String[] getNameAndLastNameAndFields(String email) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT " + COLUMN_USERNAME + ", " + COLUMN_USERAPELLIDO + ", " +
                COLUMN_PROVINCIA + ", " + COLUMN_CORREGIMIENTO +
                " FROM " + TABLE_USERS +
                " WHERE " + COLUMN_EMAIL + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email});

        String[] data = new String[4];
        if (cursor.moveToFirst()) {
            int columnIndexUsername = cursor.getColumnIndex(COLUMN_USERNAME);
            int columnIndexUserApellido = cursor.getColumnIndex(COLUMN_USERAPELLIDO);
            int columnIndexProvincia = cursor.getColumnIndex(COLUMN_PROVINCIA);
            int columnIndexCorregimiento = cursor.getColumnIndex(COLUMN_CORREGIMIENTO);

            data[0] = cursor.getString(columnIndexUsername);
            data[1] = cursor.getString(columnIndexUserApellido);
            data[2] = cursor.getString(columnIndexProvincia);
            data[3] = cursor.getString(columnIndexCorregimiento);
        }

        cursor.close();
        db.close();
        return data;
    }



}
