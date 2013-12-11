package com.mycompany.myapp4; 

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.view.View.*;
import android.content.*;
public class MainActivity extends Activity implements OnClickListener
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		Button b = (Button) findViewById(R.id.alert);
		b.setOnClickListener(this);
    }
	public void onClick(View v){
		AlertDialog.Builder al = new AlertDialog.Builder(this);
		al
		.setTitle("Continue?")
		.setMessage("A built-in camera is required to take a photo. Do you want to continue?")
		.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface d, int x){
			}
		})
		.setNegativeButton("No", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface d, int x){
			}
		})
		.show();
	}
}
