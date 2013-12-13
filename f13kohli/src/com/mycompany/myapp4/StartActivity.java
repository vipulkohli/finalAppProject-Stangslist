package com.mycompany.myapp4;

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

/**
 * @author Vipul Kohli
 * Click listeners, ListView
 *
 * @author savant-pan (viewPagerIndicatorView)
 * 
 */
public class StartActivity extends Activity{
	private ListView mListView;
	private ArrayAdapter mOps1, mOps2, mOps3, mOps4, mOps5;
	private String[] mCampus, mNil, mMainSale, mMainPlaces;
	private String choice; 
	//private int listLayout;
	private ArrayList<String>nextList;
	public ListView getListView(){
		return mListView;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);
		//startActivity(new Intent(this, GridActivity.class));
		String [] heads = {"For sale","Lost & Found","Places" };
		String[] campus = {"SMU Main Campus", "SMU-in-Plano", "SMU-in-Taos", "Lost something?", "Found Something?", "Offering a job?"};
		ArrayList<String>location = new ArrayList<String>();
		ArrayList<String>cats = new ArrayList<String>();
		for(String c : campus)
		    location.add(c);
		for(String c : heads)
		    cats.add(c);
		nextList = cats;
		final String [] nil = {};
		final String [] mainSale = {"Watch", "Samsung TV", "MacBook Air", "iPad Mini", "iPhone 5S Gold AT&T", "CSS: The Definitive Guide"};
		final String [] mainPlaces = {"Restaurants", "Gas Stations", "Housing"};
		mNil = nil;
		mMainSale = mainSale;
		mMainPlaces = mainPlaces;
		//	setContentView(R.layout.list_row);
		//set ViewPagerIndicatorView

		//the whole tabbing concept is from allthelucky/savant-pan
		//all ListView is Vipul's original work
		final ArrayList<View> tablays = new ArrayList <View>();
		final ArrayList<String> tabtext = new ArrayList <String>();

		ListView lv = (ListView) findViewById(R.id.listView);
		//lv.setAdapter(ad); //use setAdapter to make listView visible
    	final ArrayAdapter<String>empty = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, nil);
		//lv.setAdapter(ad); //use setAdapter to make listView visible
		mListView = lv;
		int listLayout= R.layout.campus;
		mListView.setAdapter(getAdapter(listLayout, location, "campus"));
	}
	public BaseAdapter getAdapter(int item, ArrayList<String>inList, String type)
	{
		final ArrayList<String> values = inList;
		final String category = type;
		final int itemLayout = item;
		return new BaseAdapter(){
			public int getCount(){
				return values.size();
			}
			public Object getItem(int pos){
				return values.get(pos);
			}
			public long getItemId(int pos){
				return 0;
			}
			public void setPlusButton(View v)
			{
				final View view = v;
				final Button plus = (Button) view.findViewById(R.id.plus);
				final Button minus = (Button) view.findViewById(R.id.minus);
				plus.setVisibility(View.VISIBLE);
				minus.setVisibility(View.VISIBLE);
				OnClickListener open = new OnClickListener(){
					public void onClick(View v){
						TextView details = (TextView) view.findViewById(R.id.details);
						if(plus.getVisibility() == View.VISIBLE){
							details.setText(":8888888888888888998999999999");
							details.setVisibility(View.VISIBLE);
							plus.setVisibility(View.INVISIBLE);
						}
						else{
							details.setVisibility(View.GONE);
							plus.setVisibility(View.VISIBLE);
						}
					}
				};
				plus.setOnClickListener(open);
				minus.setOnClickListener(open);


			}
			public View getView(int pos, View convertView, ViewGroup parent){
				LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
				final View view = inflater.inflate(itemLayout, null);
				Button tv =  (Button) view.findViewById(R.id.select);
				tv.setText(values.get(pos));
				tv.setOnClickListener(new OnClickListener(){
						public void onClick(View v){
							if(category != "heads")
							    mListView.setAdapter(getAdapter(R.layout.campus, nextList, "heads"));
							else
								startActivity(new Intent(getApplicationContext(), GridActivity.class));
						}
					});
				return view;
			}

		};
	}
}
