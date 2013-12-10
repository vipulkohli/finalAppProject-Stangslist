package com.mycompany.myapp4;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.AdapterView.*;
import android.widget.*;
import android.content.*;

public class MainActivity extends Activity
{
    Spinner mSpin;
	GridAdapter mCatsAdapter;
	GridAdapter mBuildsAdapter;
	GridView mGrid;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		mSpin = (Spinner) findViewById(R.id.spin);
		final String [] spinOps = {"Find by Category", "Find by Building"};
		Stri