package com.example.reimbursementapp;

import com.example.database.Receipt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
 
public class SingleListItem extends Activity{
	
	static Receipt myReceipt;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_manage_receipts);
 
        TextView txtProduct = (TextView) findViewById(R.id.product_label);
 
        Intent i = getIntent();
        // getting attached intent data
        String rec_name = i.getStringExtra("rec_name");
        
        myReceipt = null;
        for(int j = 0; j<ManageReceiptsActivity.receipt_names.size(); j++) {
        	if(ManageReceiptsActivity.receipt_names.get(j).equals(rec_name)) {
        		myReceipt = ManageReceiptsActivity.receipts.get(j);
        		break;
        	}
        }
        
        String out = myReceipt.toString();
        
        TextView tv = (TextView)findViewById(R.id.product_label);
        tv.setText(out);
        
    }
    
    public void handleDeletion(View view) {
    	HomeScreenActivity.db.deleteReceipt(myReceipt);
        ManageReceiptsActivity.updateLists();
        ManageReceiptsActivity.myAdapter.notifyDataSetChanged();
        this.onBackPressed();
    }
    
    public void goBack(View view) {
    	this.onBackPressed();
    }
    
    public void markAsPaid(View view) {
    	HomeScreenActivity.db.deleteReceipt(myReceipt);
    	HomeScreenActivity.db.addReceipt(new Receipt(myReceipt.getReceipt_id(), myReceipt.getReceipt_name(),
    						myReceipt.getUser_id(), myReceipt.getReceipt_desc(), myReceipt.getReceipt_date(),
    						myReceipt.getPayable_amount(), "true", "true"));
        ManageReceiptsActivity.updateLists();
        PaymentHistoryActivity.updateHistory();
        ManageReceiptsActivity.myAdapter.notifyDataSetChanged();
        this.onBackPressed();
    }
}