package sg.edu.rp.c346.id20037987.myexperiencewithzooanimals;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "animalExperience.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_ANIMALS = "animals";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EXPERIENCE = "experience";
    private static final String COLUMN_LOCATION = "location";
    private static final String COLUMN_STARS = "stars";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // CREATE TABLE Animal
        // (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT,
        // singers TEXT, stars INTEGER, year INTEGER );
        String createAnimalsTableSql = "CREATE TABLE " + TABLE_ANIMALS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT, "
                + COLUMN_EXPERIENCE + " TEXT, "
                + COLUMN_LOCATION + " INTEGER, "
                + COLUMN_STARS + " INTEGER )";
        db.execSQL(createAnimalsTableSql);
        Log.i("info", createAnimalsTableSql + "\ncreated tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANIMALS);
        onCreate(db);
    }

    public long insertAniExp(String name, String exp, String locate, int stars) {
        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_EXPERIENCE, exp);
        values.put(COLUMN_LOCATION, locate);
        values.put(COLUMN_STARS, stars);
        // Insert the row into the TABLE_ANIMALS
        long result = db.insert(TABLE_ANIMALS, null, values);
        // Close the database connection
        db.close();
        Log.d("SQL Insert","" + result);
        return result;
    }

    public ArrayList<AnimalExperience> getAllAniExp() {
        ArrayList<AnimalExperience> aniExpList = new ArrayList<AnimalExperience>();
        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_NAME + "," + COLUMN_EXPERIENCE + ","
                + COLUMN_LOCATION + ","
                + COLUMN_STARS + " FROM " + TABLE_ANIMALS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String exp = cursor.getString(2);
                String locate = cursor.getString(3);
                int stars = cursor.getInt(4);

                AnimalExperience newAniExp = new AnimalExperience(id, name, exp, locate, stars);
                aniExpList.add(newAniExp);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return aniExpList;
    }

    public ArrayList<AnimalExperience> getAllAniExpByStars(int starsFilter) {
        ArrayList<AnimalExperience> aniExpList = new ArrayList<AnimalExperience>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID, COLUMN_NAME, COLUMN_EXPERIENCE, COLUMN_LOCATION, COLUMN_STARS};
        String condition = COLUMN_STARS + ">= ?";
        String[] args = {String.valueOf(starsFilter)};

        //String selectQuery = "SELECT " + COLUMN_ID + ","
        //            + COLUMN_TITLE + ","
        //            + COLUMN_SINGERS + ","
        //            + COLUMN_YEAR + ","
        //            + COLUMN_STARS
        //            + " FROM " + TABLE_SONG;

        Cursor cursor;
        cursor = db.query(TABLE_ANIMALS, columns, condition, args, null, null, null, null);

        // Loop through all rows and add to ArrayList
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String exp = cursor.getString(2);
                String locate = cursor.getString(3);
                int stars = cursor.getInt(4);

                AnimalExperience newAniExp = new AnimalExperience(id, name, exp, locate, stars);
                aniExpList.add(newAniExp);
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();
        return aniExpList;
    }

    public int updateAniExp(AnimalExperience data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, data.getName());
        values.put(COLUMN_EXPERIENCE, data.getExp());
        values.put(COLUMN_LOCATION, data.getLocation());
        values.put(COLUMN_STARS, data.getStars());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_ANIMALS, values, condition, args);
        db.close();
        return result;
    }

    public int deleteAniExp(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_ANIMALS, condition, args);
        db.close();
        return result;
    }

}
