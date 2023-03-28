package model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDD extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "TP1.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "Contact";

    public static final String COL_ID = "id";
    public static final String COL_NOM = "nom";
    public static final String COL_PRENOM = "prenom";
    public static final String COL_AGE = "age";
    public static final String COL_GENDER = "gender";
    public static final String COL_NUMBER = "number";
    public static final String COL_ADRESSE = "adresse";

    public BDD(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Créer la table pour stocker vos données
        String createTableQuery = "CREATE TABLE contact ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nom TEXT,"
                + "prenom TEXT,"
                + "age INTEGER,"
                + "number TEXT,"
                + "gender TEXT,"
                + "adresse TEXT);";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Mettre à jour la base de données en cas de modification de la structure de la table
    }

    public void insertData(String nom, String prenom, int age, String number, String adresse, String gender) {
        SQLiteDatabase db = this.getWritableDatabase();
        String insertQuery = "INSERT INTO contact (nom, prenom, number, adresse, gender, age) VALUES ('"
                + nom + "', '"
                + prenom + "', '"
                + number + "', '"
                + adresse + "', '"
                + gender + "', "
                + age + ");";
        db.execSQL(insertQuery);
        db.close();
    }

    public void deleteData(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteQuery = "DELETE FROM contact WHERE id = " + id;
        db.execSQL(deleteQuery);
        db.close();
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM contact";
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;
    }
}
