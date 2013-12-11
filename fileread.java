package com.mycompany.myapp;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.net.*;
import java.io.*;
import java.util.*;
import android.graphics.*;
import android.graphics.drawable.*;
import org.json.*;

public class MainActivity extends Activity
{
	Bitmap img = null;
	URL url;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
		try{
			super.onCreate(savedInstanceState);
			InputStreamReader is = new InputStreamReader(getResources().openRawResource(R.raw.hello));
			BufferedReader reader = new BufferedReader(is);
			String line = null;
			setContentView(R.layout.main);
			TextView tv = (TextView) findViewById(R.id.json);
			tv.setText(reader.readLine());

		}
		catch(MalformedURLException e){
      
		}
		catch(IOException e)
		{}
		catch(Exception e)
		{}
    }

}
