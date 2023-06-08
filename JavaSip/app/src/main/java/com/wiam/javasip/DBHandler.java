package com.wiam.javasip;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "javasip";
    // below int is our database version
    private static final int DB_VERSION = 1;
    // below variable is for our table name.
    private static final String TABLE_NAME = "orders";
    // below variable is for our id column.
    private static final String ID_COL = "id";
    // below variable is for our coffee  name column
    private static final String NAME_COL = "name";
    // below variable is for our coffee size column.
    private static final String SIZE_COL = "size";
    // below variable for our cream option column.
    private static final String CREAM_COL = "cream";
    // below variable is for our price column.
    private static final String PRICE_COL = "PRICE";

    private int val;

    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
// on below line we are creating
// an sqlite query and we are
// setting our column names
// along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " (" + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + SIZE_COL + " TEXT,"
                + CREAM_COL + " TEXT,"
                + PRICE_COL + " INTEGER)";
// at last we are calling a exec sql
// method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new order to our sqlite database.
    public void addNewOrder(String name, String size, String cream, String price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
// on below line we are passing all values
// along with its key and value pair.
        values.put(NAME_COL, name);
        values.put(SIZE_COL, size);
        values.put(CREAM_COL, cream);
        values.put(PRICE_COL, price);
// after adding all values we are passing
// content values to our table.
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    // we have created a new method for reading all the courses.
    public ArrayList<OrderModal> readOrders() {
        // on below line we are creating a database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();
        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorOrders = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        // on below line we are creating a new array list.
        ArrayList<OrderModal> OrderModalArrayList = new ArrayList<>();
        // moving our cursor to first position.
        if (cursorOrders.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                OrderModalArrayList.add(new OrderModal(cursorOrders.getString(1),
                        cursorOrders.getString(4),
                        cursorOrders.getString(2),
                        cursorOrders.getString(3)));
            } while (cursorOrders.moveToNext());
// moving our cursor to next.
        }
// at last closing our cursor
// and returning our array list.
        cursorOrders.close();
        return OrderModalArrayList;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
   public void cancelOrder() {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }
    // Sum total items price
    public int sumPriceCartItems() {
        int val=0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select "+ PRICE_COL+ "  from " + TABLE_NAME, null);

        if(cursor.moveToFirst())
            do {
                val += cursor.getInt(0);
            } while (cursor.moveToNext());

        cursor.close();
        db.close();
        return val;
    }
}

