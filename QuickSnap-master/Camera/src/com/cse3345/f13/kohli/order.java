package com.cse3345.f13.kohli;

import java.util.*;
import java.net.*;
import java.io.*;
import java.lang.*;
import android.view.*;       
import android.app.*;       
import android.os.*;
import android.content.*; 
import android.widget.*;
import android.widget.AdapterView.*;
import android.view.View.*;
import android.graphics.Color;
import com.allthelucky.common.view.R;

public class order extends Activity
{
    TextView mTitle;
	TextView mPrice;
	TextView paytext;
	Button mSubmit;
	Button mChoosePayButton;
	Button mChooseShipButton;
	TextView mThanks;
	LinearLayout editScreen = (LinearLayout) findViewById(R.id.orderscreen);
	RadioGroup shipOps = (RadioGroup) findViewById(R.id.radioship);
	RadioGroup payOps = (RadioGroup) findViewById(R.id.radiopay);
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
		setContentView(R.layout.order);
		mTitle = (TextView) findViewById(R.id.title);
		mTitle.setText(i.getStringExtra("Title"));
		mPrice = (TextView) findViewById(R.id.price);
		mPrice.setText(i.getStringExtra("Price"));
		mSubmit = (Button) findViewById(R.id.submit);
		mChoosePayButton = (Button) findViewById(R.id.paybut);
		mChooseShipButton = (Button) findViewById(R.id.shipbut);
		final LinearLayout editScreen = (LinearLayout) findViewById(R.id.orderscreen);
		final TextView thx = (TextView) findViewById(R.id.thanks);
		final RadioGroup shipOps = (RadioGroup) findViewById(R.id.radioship);
		final RadioGroup payOps = (RadioGroup) findViewById(R.id.radiopay);
		paytext = (TextView) findViewById(R.id.paytext);
		RadioButton ppal = (RadioButton) findViewById(R.id.ppal);
		RadioButton card = (RadioButton) findViewById(R.id.card);
		ppal.setOnClickListener(radioListener);
		card.setOnClickListener(radioListener);
		mSubmit.setOnClickListener(new View.OnClickListener(){
				public void onClick(View v)
				{
					editScreen.setVisibility(View.INVISIBLE);
					thx.setVisibility(View.VISIBLE);
				}
			});
		mChoosePayButton.setOnClickListener(new View.OnClickListener(){
				public void onClick(View v)
				{
					if (payOps.getVisibility() != View.VISIBLE)
						payOps.setVisibility(View.VISIBLE);
					else
						payOps.setVisibility(View.GONE);
				}
			});
		mChooseShipButton.setOnClickListener(new View.OnClickListener(){
				public void onClick(View v)
				{
					if (shipOps.getVisibility() != View.VISIBLE)
						shipOps.setVisibility(View.VISIBLE);
					else
						shipOps.setVisibility(View.GONE);
				}
			});
	}
	private OnClickListener radioListener = new OnClickListener(){
		public void onClick(View v){
			RadioButton rb = (RadioButton) v;
			paytext.setText("Payment Method: " + rb.getText());
			paytext.setTextColor(Color.RED);
		}
	};
}

