package com.cse3345.f13.kohli;
import com.lightbox.android.camera.R;
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
public class CheckActivity extends Activity{
	private ListView mListView;
	private ArrayAdapter mOps1, mOps2, mOps3, mOps4, mOps5;
	private String[] mCampus, mNil, mMainSale, mMainPlaces;
	private String choice; 
	private ArrayList<String>mTimes;
	private ArrayList<String>mDays;
	private ArrayList<String>mSchedule;
	//private int listLayout;
	private ArrayList<String>nextList;
	public ListView getListView(){
		return mListView;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		String [] heads = {"Sunday","Monday","Tuesday", "Wednesday","Thursday","Friday", "Saturday"};
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
		mTimes = new ArrayList<String>();
		mDays = new ArrayList<String>();
		mDays = cats;
		mSchedule = new ArrayList<String>();
		mSchedule = cats;
		mListView.setAdapter(getAdapter(R.layout.times, cats, "heads"));
		
		{
			Toast.makeText(getApplicationContext(), mSchedule.toString(), Toast.LENGTH_LONG).show();
			
		}
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
	public void setPlusButton(View v, int inPos)
	{
		final View view = v;
		final int pos = inPos;
		final Button plus = (Button) view.findViewById(R.id.plus);
		final Button minus = (Button) view.findViewById(R.id.minus);
		plus.setVisibility(View.VISIBLE);
		minus.setVisibility(View.VISIBLE);
		final LinearLayout checks = (LinearLayout) view.findViewById(R.id.checks);
		final int [] checkids = {R.id.checkBox1, R.id.checkBox2,  R.id.checkBox3};
		OnClickListener open = new OnClickListener(){
				public void onClick(View v){
					
					if(plus.getVisibility() == View.VISIBLE){
	                      plus.setVisibility(View.INVISIBLE);
						checks.setVisibility(View.VISIBLE);
					}
					else{
                        checks.setVisibility(View.GONE);
						plus.setVisibility(View.VISIBLE);
						for(int check: checkids){
							CheckBox box = (CheckBox) view.findViewById(check);
							if (box.isChecked())
								mTimes.add(box.getText().toString());
						}
						mSchedule.set(pos, mDays.get(pos) + ": " + mTimes.toString().substring(1, mTimes.toString().length() - 1));		
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
		if (category != "campus")
		     setPlusButton(view, pos);
		tv.setText(values.get(pos));
		tv.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				//mListView.setAdapter(getAdapter(R.layout.list_row, nextList, "heads"));
			}
		});
		return view;
	}

};
	}
	}

