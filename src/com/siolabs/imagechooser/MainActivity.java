package com.siolabs.imagechooser;

import java.io.FileNotFoundException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

	ImageView image ;
	private Bitmap bitmap;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		image= (ImageView)findViewById(R.id.imageView1);
	}
	
	public void pickImage(View view){
		
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		intent.addCategory(Intent.CATEGORY_OPENABLE);
		startActivityForResult(intent, 24);
		
	}
	
	
	@Override
	protected void onActivityResult(int reqCode, int resCode, Intent data){
		InputStream stream = null;
		
		try{
			if(bitmap != null){
				bitmap.recycle();
			}
			
			stream = getContentResolver().openInputStream(data.getData());
			bitmap = BitmapFactory.decodeStream(stream);
			
			image.setImageBitmap(bitmap);
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
		
	}
}
