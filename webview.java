package com.mycompany.camera;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.webkit.*;

public class MainActivity extends Activity
{
   WebView web;
   /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		web = (WebView) findViewById(R.id.web);
		web.loadUrl("http://yahoo.com");
    }
}
