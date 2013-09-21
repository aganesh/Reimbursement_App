package com.example.reimbursementapp;

import java.util.ArrayList;

import com.example.database.DatabaseHandler;
import com.example.database.Receipt;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ManageReceiptsActivity  extends ListActivity {
	
	static ArrayList<Receipt> receipts = new ArrayList<Receipt>();
    static ArrayList<String> receipt_names = new ArrayList<String>();
    static ArrayAdapter<String> myAdapter;
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.setContentView(R.layout.activity_manage_receipts);
 
        // storing string resources into ArrayLists
        updateLists();
 
        // Binding resources Array to ListAdapter
        myAdapter = new ArrayAdapter<String>(this, R.layout.activity_single_list_item, R.id.label, receipt_names);
        this.setListAdapter(myAdapter);
 
        ListView lv = getListView();
 
        // listening to single list item on click
        lv.setOnItemClickListener(new OnItemClickListener() {
          public void onItemClick(AdapterView<?> parent, View view,
              int position, long id) {
 
              // selected item
              String rec_name = ((TextView) view).getText().toString();
              
              // Launching new Activity on selecting single List Item
              Intent i = new Intent(getApplicationContext(), SingleListItem.class);
              // sending data to new activity
              i.putExtra("rec_name", rec_name);
              startActivity(i);
 
          }
        });
    }
	
	public static void updateLists() {
		receipts.clear(); receipt_names.clear();
        receipts = (ArrayList<Receipt>) HomeScreenActivity.db.getAllUnconfReceipts();
        
        for(int i = 0; i<receipts.size(); i++) {
        	receipt_names.add(receipts.get(i).getReceipt_name() + " (" + receipts.get(i).getReceipt_date() + ")");
        }
	}
	
	
}

