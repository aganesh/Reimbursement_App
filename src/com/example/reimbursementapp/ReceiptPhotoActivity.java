package com.example.reimbursementapp;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class ReceiptPhotoActivity extends Activity {

	ImageView iv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_receipt_photo);
		
		iv = (ImageView) findViewById(R.id.imageView1);
		Button btn = (Button) findViewById(R.id.click);
		btn.setOnClickListener(new OnClickListener(){
			
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(intent, 0);
				
			}
			
			
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if(requestCode == 0){
			
			if (data != null){
				Bitmap theImage = (Bitmap) data.getExtras().get("data");
				iv.setImageBitmap(theImage);
			}
			
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_receipt_photo, menu);
		return true;
	}

}





