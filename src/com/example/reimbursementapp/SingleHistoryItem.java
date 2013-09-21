package com.example.reimbursementapp;

import com.example.database.Receipt;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class SingleHistoryItem extends Activity {

	static Receipt myPayment;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_payment_history);
 
        TextView txtProduct = (TextView) findViewById(R.id.product_label);
 
        Intent i = getIntent();
        // getting attached intent data
        String rec_name = i.getStringExtra("rec_name");
        
        myPayment = null;
        for(int j = 0; j<PaymentHistoryActivity.payment_names.size(); j++) {
        	if(PaymentHistoryActivity.payment_names.get(j).equals(rec_name)) {
        		myPayment = PaymentHistoryActivity.payments.get(j);
        		break;
        	}
        }
        
        String out = myPayment.toString();
        
        TextView tv = (TextView)findViewById(R.id.product_label);
        tv.setText(out);
        
    }
    
    public void handleDeletion(View view) {
    	HomeScreenActivity.db.deleteReceipt(myPayment);
    	PaymentHistoryActivity.updateHistory();
    	PaymentHistoryActivity.myAdapter1.notifyDataSetChanged();
        this.onBackPressed();
    }
    
    public void goBack(View view) {
    	this.onBackPressed();
    }

}
