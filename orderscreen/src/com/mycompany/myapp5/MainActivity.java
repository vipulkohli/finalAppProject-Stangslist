package com.mycompany.myapp5;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.view.View.*;
import java.lang.*;
import java.util.*;
import android.graphics.*;

public class MainActivity extends Activity
{
    TextView shiptext;
	TextView paytext;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		Button submit = (Button) findViewById(R.id.submit);
		Button butPay = (Button) findViewById(R.id.paybut);
		Button butShip = (Button) findViewById(R.id.shipbut);
	    shiptext = (TextView) findViewById(R.id.confship);
		paytext = (TextView) findViewById(R.id.confpay);
		final LinearLayout editScreen = (LinearLayout) findViewById(R.id.orderscreen);
		final TextView thx = (TextView) findViewById(R.id.thanks);
		final RadioGroup shipOps = (RadioGroup) findViewById(R.id.radioship);
		final RadioGroup payOps = (RadioGroup) findViewById(R.id.radiopay);
		RadioButton ppal = (RadioButton) findViewById(R.id.ppal);
		RadioButton card = (RadioButton) findViewById(R.id.card);
		ppal.setOnClickListener(radioListener);
		card.setOnClickListener(radioListener);
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
					if (payOps.getVisibility() != View.VISIBLE)
					    payOps.setVisibility(View.VISIBLE);
					else
					    payOps.setVisibility(View.GONE);
				}
			});
		butShip.setOnClickListener(new View.OnClickListener(){
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
