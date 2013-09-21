package com.example.reimbursementapp;

import java.util.ArrayList;

import com.example.database.Receipt;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class PaymentHistoryActivity extends ListActivity {

	static ArrayList<Receipt> payments = new ArrayList<Receipt>();
    static ArrayList<String> payment_names = new ArrayList<String>();
    static ArrayAdapter<String> myAdapter1;
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.setContentView(R.layout.activity_manage_receipts);
 
        // storing string resources into ArrayLists
        updateHistory();
 
        // Binding resources Array to ListAdapter
        myAdapter1 = new ArrayAdapter<String>(this, R.layout.activity_single_history_item, R.id.label, payment_names);
        this.setListAdapter(myAdapter1);
 
        ListView lv = getListView();
 
        // listening to single list item on click
        lv.setOnItemClickListener(new OnItemClickListener() {
          public void onItemClick(AdapterView<?> parent, View view,
              int position, long id) {
 
              // selected item
              String rec_name = ((TextView) view).getText().toString();
              
              // Launching new Activity on selecting single List Item
              Intent i = new Intent(getApplicationContext(), SingleHistoryItem.class);
              // sending data to new activity
              i.putExtra("rec_name", rec_name);
              startActivity(i);
 
          }
        });
    }
	
	public static void updateHistory() {
		payments.clear(); payment_names.clear();
		payments = (ArrayList<Receipt>) HomeScreenActivity.db.getAllPaidReceipts();
        
		for(int i = 0; i<payments.size(); i++) {
			payment_names.add(payments.get(i).getReceipt_name() + " (" + payments.get(i).getReceipt_date() + ")");
		}
		
	}
	
	
	
	
}
