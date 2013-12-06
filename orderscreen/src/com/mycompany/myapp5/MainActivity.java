package com.mycompany.myapp5;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.view.View.*;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle spavedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		Button submit = (Button) findViewById(R.id.submit);
		Button butPay = (Button) findViewById(R.id.paybut);
		Button butShip = (Button) findViewById(R.id.shipbut);
		final LinearLayout editScreen = (LinearLayout) findViewById(R.id.orderscreen);
		final TextView thx = (TextView) findViewById(R.id.thanks);
		final RadioGroup shipOps = (RadioGroup) findViewById(R.id.radioship);
		final RadioGroup payOps = (RadioGroup) findViewById(R.id.radiopay);
		submit.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v)
			{
				editScreen.setVisibility(View.INVISIBLE);
				thx.setVisibility(View.VISIBLE);
			}
		});
		butPay.setOnClickListener(new View.OnClickListener(){
				public void onClick(View v)
				{
					payOps.setVisibility(View.VISIBLE);
				}
			});
		butShip.setOnClickListener(new View.OnClickListener(){
				public void onClick(View v)
				{
					shipOps.setVisibility(View.VISIBLE);
				}
			});
    }
}
