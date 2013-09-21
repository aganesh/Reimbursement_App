package com.example.reimbursementapp;

import com.example.database.DatabaseHandler;
import com.example.database.Receipt;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class HomeScreenActivity extends Activity {

	static DatabaseHandler db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		db = new DatabaseHandler(this);
				
		super.onCreate(savedInstanceState);
		storeData(db);
		setContentView(R.layout.activity_home_screen);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home_screen, menu);
		return true;
	}
	
	public void initiateReceiptPhoto(View view){
		Intent intent = new Intent(this, ReceiptPhotoActivity.class);
		startActivity(intent);
	}
	
	public void goToReceipts(View view) {
		Intent intent = new Intent(this, ManageReceiptsActivity.class);
		startActivity(intent);
	}
	
	public void goToHistory(View view) {
		Intent intent = new Intent(this, PaymentHistoryActivity.class);
		startActivity(intent);
	}
	
	public void toastSubmitReceipt(View view) {
		db.addReceipt(new Receipt((int)(Math.random()*9999), "NewName", 1, "NewDesc", "NewDate", 100.0, "false", "false"));
		ManageReceiptsActivity.updateLists();
		PaymentHistoryActivity.updateHistory();
		Toast.makeText(getApplicationContext(), "New Receipt Created and Added to Database", Toast.LENGTH_LONG).show();
	}
	
	public static void storeData(DatabaseHandler db) {
		db.addReceipt(new Receipt(3231, "Dinner", 1, "Client Dinner in PIT", "01/10/2011", 10.0, "false", "false"));
		db.addReceipt(new Receipt(2532, "Drinks", 1, "Drinks in Manhattan", "11/21/2012", 90.0, "false", "false"));
		db.addReceipt(new Receipt(6765, "Lunch", 1, "Client Lunch in Jersey", "12/4/2012", 87.75, "false", "false"));
		db.addReceipt(new Receipt(7685, "Supplies", 1, "Office Supplies for HR", "10/01/2012", 20.75, "false", "true"));
		db.addReceipt(new Receipt(1446, "Cab", 1, "Cab to JFK", "12/23/2011", 43.0, "false", "false"));
		db.addReceipt(new Receipt(8852, "Misc", 1, "Random Expenses", "09/15/2012", 8.0, "true", "true"));
	}

}
