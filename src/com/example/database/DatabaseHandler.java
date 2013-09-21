package com.example.database;
 
import java.util.ArrayList;
import java.util.List;
 
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {
	// Database version, name, and table names.
	private static final int DATABASE_VERSION = 1;
	
	private static final String DATABASE_NAME = "SubmitDB";
	private static final String TABLE_RECEIPTS = "Receipts";
	
	// Columns in database.
	private static final String KEY_RECEIPT_ID = "Receipt_ID";
	private static final String KEY_NAME = "Receipt_Name";
	private static final String KEY_USER_ID = "User_ID";
	
	private static final String KEY_RECEIPT_DESC = "Receipt_Desc";
	private static final String KEY_RECEIPT_DATE = "Receipt_Date";
	private static final String KEY_AMOUNT = "Amount";
	
	private static final String KEY_USER_CONF = "User_Conf";
	private static final String KEY_COMPANY_CONF = "Company_conf";
	
	public DatabaseHandler(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	// Creates tables (overrides super classes' definition).
	public void onCreate(SQLiteDatabase db)
	{		
		String CREATE_RECEIPT_TABLE = "CREATE TABLE " + TABLE_RECEIPTS + "("
				+ KEY_RECEIPT_ID + " INTEGER PRIMARY KEY," 
				+ KEY_NAME + " TEXT,"
				+ KEY_USER_ID + " INTEGER," 
				+ KEY_RECEIPT_DESC + " TEXT,"
				+ KEY_RECEIPT_DATE + " TEXT,"
				+ KEY_AMOUNT + " DOUBLE," 
				+ KEY_USER_CONF + " TEXT," 
				+ KEY_COMPANY_CONF + " TEXT" + ")";
		db.execSQL(CREATE_RECEIPT_TABLE);
	}
	
	// Upgrading database.
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECEIPTS);
 
        onCreate(db);
    }
	
	// C for create.
    public void addReceipt(Receipt receipt) 
    {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        
        values.put(KEY_RECEIPT_ID, receipt.getReceipt_id());
        values.put(KEY_NAME, receipt.getReceipt_name());        
        values.put(KEY_USER_ID, receipt.getUser_id());        
        values.put(KEY_AMOUNT, receipt.getPayable_amount());
        values.put(KEY_RECEIPT_DESC, receipt.getReceipt_desc());
        values.put(KEY_RECEIPT_DATE, receipt.getReceipt_date());
        values.put(KEY_USER_CONF, receipt.getUser_conf());
        values.put(KEY_COMPANY_CONF, receipt.getCompany_conf());
        
        db.insert(TABLE_RECEIPTS, null, values);
        Log.d("Content values:", values.toString());
        db.close();
    }
    
    // R is for read.
    public Receipt getReceipt(int id) 
    {
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(
        		TABLE_RECEIPTS, 
        		new String[] { KEY_RECEIPT_ID, KEY_NAME, KEY_USER_ID, 
        			KEY_RECEIPT_DESC, KEY_RECEIPT_DATE, 
        			KEY_AMOUNT, KEY_USER_CONF, KEY_COMPANY_CONF }, 
        		KEY_RECEIPT_ID + "=?",
                new String[] { String.valueOf(id) }, 
                null, null, null, null);
        
        if (cursor != null)
            cursor.moveToFirst();
 
        Receipt receipt = new Receipt(
        		Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), 
        		Integer.parseInt(cursor.getString(2)),
        		cursor.getString(3),
        		cursor.getString(4),
                Double.parseDouble(cursor.getString(5)),
                cursor.getString(6),
                cursor.getString(7)
        	);
        		

        cursor.close();
        return receipt;
    }
    
    public List<Receipt> getAllPaidReceipts()
    {
    	List<Receipt> paidReceipts = new ArrayList<Receipt>();
    	
        SQLiteDatabase db = this.getReadableDatabase();
        
        Cursor cursor = db.query(
        		TABLE_RECEIPTS, 
        		new String[] { KEY_RECEIPT_ID, KEY_NAME, KEY_USER_ID, 
        			KEY_RECEIPT_DESC, KEY_RECEIPT_DATE,
        			KEY_AMOUNT, KEY_USER_CONF, KEY_COMPANY_CONF }, 
        		KEY_USER_CONF + "=? AND " + KEY_COMPANY_CONF + "=?",
                new String[] { "true", "true" }, 
                null, null, null, null);
    	
        if (cursor.moveToFirst()) {
            do {
                Receipt receipt = new Receipt();
                receipt.setReceipt_id(Integer.parseInt(cursor.getString(0)));
                receipt.setReceipt_name(cursor.getString(1));
                receipt.setUser_id(Integer.parseInt(cursor.getString(2)));
                receipt.setReceipt_desc(cursor.getString(3));
                receipt.setReceipt_date(cursor.getString(4));
                receipt.setPayable_amount(Double.parseDouble(cursor.getString(5)));
                receipt.setUser_conf(cursor.getString(6));
                receipt.setCompany_conf(cursor.getString(7));
                
                paidReceipts.add(receipt);
            } while (cursor.moveToNext());
        }
        
        cursor.close();
    	return paidReceipts;
    }
    
    public List<Receipt> getAllUnconfReceipts()
    {
    	List<Receipt> unconfReceipts = new ArrayList<Receipt>();
    	
        SQLiteDatabase db = this.getReadableDatabase();
        
        Cursor cursor = db.query(
        		TABLE_RECEIPTS, 
        		new String[] { KEY_RECEIPT_ID, KEY_NAME, KEY_USER_ID, 
        			KEY_RECEIPT_DESC, KEY_RECEIPT_DATE,
        			KEY_AMOUNT, KEY_USER_CONF, KEY_COMPANY_CONF }, 
        		KEY_USER_CONF + "=? OR " + KEY_COMPANY_CONF + "=?",
                new String[] { "false", "false" }, 
                null, null, null, null);
    	
        if (cursor.moveToFirst()) {
            do {
                Receipt receipt = new Receipt();
                receipt.setReceipt_id(Integer.parseInt(cursor.getString(0)));
                receipt.setReceipt_name(cursor.getString(1));
                receipt.setUser_id(Integer.parseInt(cursor.getString(2)));
                receipt.setReceipt_desc(cursor.getString(3));
                receipt.setReceipt_date(cursor.getString(4));     
                receipt.setPayable_amount(Double.parseDouble(cursor.getString(5)));
                receipt.setUser_conf(cursor.getString(6));
                receipt.setCompany_conf(cursor.getString(7));
                
                unconfReceipts.add(receipt);
            } while (cursor.moveToNext());
        }
        
        cursor.close();
    	return unconfReceipts;
    }
 
    // (Reading all receipts into a list.)
    public List<Receipt> getAllReceipts() 
    {
        List<Receipt> recList = new ArrayList<Receipt>();
        
        String selectQuery = "SELECT  * FROM " + TABLE_RECEIPTS;
 
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        if (cursor.moveToFirst()) {
            do {
                Receipt receipt = new Receipt();
                receipt.setReceipt_id(Integer.parseInt(cursor.getString(0)));
                receipt.setReceipt_name(cursor.getString(1));
                receipt.setUser_id(Integer.parseInt(cursor.getString(2)));
                receipt.setReceipt_desc(cursor.getString(3));
                receipt.setReceipt_date(cursor.getString(4));
                receipt.setPayable_amount(Double.parseDouble(cursor.getString(5)));
                receipt.setUser_conf(cursor.getString(6));
                receipt.setCompany_conf(cursor.getString(7));
                Log.d("Receipt After Get:", receipt.toString());
                recList.add(receipt);
            } while (cursor.moveToNext());
        }
 
        cursor.close();
        return recList;
    }
    
    // U is for update.
    public int updateReceipt(Receipt receipt) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        
        values.put(KEY_RECEIPT_ID, receipt.getReceipt_id());
        values.put(KEY_NAME, receipt.getReceipt_name());        
        values.put(KEY_USER_ID, receipt.getUser_id());        
        values.put(KEY_AMOUNT, receipt.getPayable_amount());
        values.put(KEY_RECEIPT_DESC, receipt.getReceipt_desc());
        values.put(KEY_RECEIPT_DATE, receipt.getReceipt_date());
        values.put(KEY_USER_CONF, receipt.getUser_conf());
        values.put(KEY_COMPANY_CONF, receipt.getCompany_conf());
 
        int ret = db.update(
        		TABLE_RECEIPTS, 
        		values, 
        		KEY_RECEIPT_ID + "=?",
                new String[] { String.valueOf(receipt.getReceipt_id()) });
        
        return ret;
    }
 
    // D is for delete.
    public void deleteReceipt(Receipt receipt) {
        SQLiteDatabase db = this.getWritableDatabase();
        
        db.delete(
        		TABLE_RECEIPTS, 
        		KEY_RECEIPT_ID + "=?",
                new String[] { String.valueOf(receipt.getReceipt_id()) });
        
        db.close();
    }
 
    // Getting a count of all receipts in table.
    public int getReceiptsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_RECEIPTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        
        cursor.close();
        return cursor.getCount();
    }
}
