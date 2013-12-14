package com.allthelucky.common.view.sample;

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
import com.allthelucky.common.view.R;
import com.allthelucky.common.view.ViewPagerIndicatorView;

/**
 * @author Vipul Kohli
 * Click listeners, ListView
 *
 * @author savant-pan (viewPagerIndicatorView)
 * 
 */
public class SampleActivity extends Activity implements OnClickListener{
	private ViewPagerIndicatorView viewPagerIndicatorView, mTabs;
	private ListView mList;
	private ArrayAdapter mOps1, mOps2, mOps3, mOps4, mOps5;
	private String[] mCampus, mNil, mMainSale, mMainPlaces;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sample);
		String [] heads = {"For sale","Lost & Found","Places" };
		final String [] campus = {"SMU Main Campus", "SMU-in-Plano", "SMU-in-Taos", "Lost something?", "Found Something?", "Offering a job?"};
		final String [] nil = {};
		final String [] mainSale = {"Watch", "Samsung TV", "MacBook Air", "iPad Mini", "iPhone 5S Gold AT&T", "CSS: The Definitive Guide"};
		final String [] mainPlaces = {"Restaurants", "Gas Stations", "Housing"};
		mCampus = campus;
		mNil = nil;
		mMainSale = mainSale;
		mMainPlaces = mainPlaces;
	//	setContentView(R.layout.list_row);
		//set ViewPagerIndicatorView
		
		//the whole tabbing concept is from allthelucky/savant-pan
		//all ListView is Vipul's original work
		this.viewPagerIndicatorView = (ViewPagerIndicatorView) findViewById(R.id.viewpager_indicator_view);
		mTabs = this.viewPagerIndicatorView;
		final ArrayList<View> tablays = new ArrayList <View>();
		final ArrayList<String> tabtext = new ArrayList <String>();
		for (String s : heads){
			tablays.add(LayoutInflater.from(this).inflate(R.layout.lay4, null));
			tabtext.add(s);
		}
		this.viewPagerIndicatorView.setupLayout(tablays, tabtext);
		this.viewPagerIndicatorView.setOnClickListener(this);
		ListView lv = (ListView) findViewById(R.id.listView);
    	ArrayAdapter<String>ad = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, campus);
		lv.setAdapter(ad); //use setAdapter to make listView visible
    	final ArrayAdapter<String>empty = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, nil);
		lv.setAdapter(ad); //use setAdapter to make listView visible
		mList = lv;
		mOps1 = ad;
		mOps2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, mainSale);
		mOps3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, mainPlaces);
		
		lv.setOnItemClickListener(new OnItemClickListener(){
			
			public void onItemClick(AdapterView<?> parent, View v, int ok, long id){
				mTabs.setVisibility(View.VISIBLE);
				mList.setAdapter(mOps2);
				onClick(mTabs);
			}
		});
		
	
	}

	@Override
	public void onClick(View p1)
	{
		int tabnum = viewPagerIndicatorView.itemnum;
		String out = "" + tabnum;
		TextView tv = (TextView) findViewById(R.id.textView);
		tv.setText(out);
		final String [] values;
		switch(tabnum){
			case 0: values = mMainSale;
					break;
			case 2: values = mMainPlaces;
					break;
			default: values = mCampus;
		}
		mList.setAdapter(new BaseAdapter(){
				public int getCount(){
					return values.length;
				}
				public Object getItem(int pos){
					return values[pos];
				}
				public long getItemId(int pos){
					return 0;
				}
				public View getView(int pos, View convertView, ViewGroup parent){
					LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
					View view = inflater.inflate(R.layout.list_row, null);
					TextView tv = (TextView) view.findViewById(R.id.textView01);
					tv.setText(values[pos]);
					return view;
				}
			});	
	}

	
}
