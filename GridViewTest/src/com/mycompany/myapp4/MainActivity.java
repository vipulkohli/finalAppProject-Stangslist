package com.mycompany.myapp4;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		String [] fruits = {"Apple", "Apple", "Apple", "Apple", "Apple", "Apple", "Apple"};
		GridView g = (GridView) findViewById(R.id.grid);
		ArrayAdapter< String> ad = new ArrayAdapter < String > (this, android.R.layout.simple_list_item_1, fruits);
		g.setAdapter(ad);
    }
}
