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
import java.util.zip.*;

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

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		 super.onCreateOptionsMenu(menu);
		 MenuInflater in = getMenuInflater();
		 in.inflate(R.menu.vipul, menu);
		 return true;
		
	}
	public ArrayList<String> getArrayList(String name){
		ArrayList<String>list = new ArrayList<String>();
		String [] strarray = {"PonyPark", "QuizardQuest","Snap2Ask"};
		for(String x:strarray)
		      list.add(x);
		return list;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);
		Intent intent = getIntent();
		String arrname = intent.getStringExtra("Category");

		//Toast.makeText(getApplicationContext(), arrname, Toast.LENGTH_LONG).show();
		//startActivity(new Intent(this, GridActivity.class));
		String [] heads = {"For sale","Lost & Found","Places" };
		String[] campus = {"SMU Main Campus", "SMU-in-Plano", "SMU-in-Taos"};
		ArrayList<String>location = new ArrayList<String>();
		ArrayList<String>cats = new ArrayList<String>();
		for(String c : campus)
		    location.add(c);
		for(String c : heads)
		    cats.add(c);
		nextList = cats;
		final String [] nil = {};
		final String [] mainSale = {"Watch", "Samsung TV", "MacBook Air", "iPad Mini", "iPhone 5S Gold AT&T", "CSS: The Definitive Guide"};
		mMainSale = mainSale;
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
		if(arrname != null){
			mListView.setAdapter(getAdapter(R.layout.list_row, getArrayList(arrname), arrname));
			((TextView) findViewById(R.id.getStarted)).setText("Searching all " + arrname);
		}
		else
		    mListView.setAdapter(getAdapter(listLayout, location, "campus"));
	}
	public BaseAdapter getAdapter(int item, ArrayList<String>inList, String type)
	{
		final ArrayList<String> values = inList;
		final String category = type;
		final int itemLayout = item;
		final boolean realItems = !(category == "heads" || category == "campus");
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
				if(realItems)
					setPlusButton(view);
				String topic = values.get(pos);
				final int index = pos;
				Button tv =  (Button) view.findViewById(R.id.select);
				tv.setText(topic);
				tv.setOnClickListener(new OnClickListener(){
						public void onClick(View v){
							if(category == "campus")
							    mListView.setAdapter(getAdapter(R.layout.campus, nextList, "heads"));
							else if(category=="heads"){
								Intent i = new Intent(getApplicationContext(), GridActivity.class);
								i.putExtra("Topic", index);
								startActivity(i);
							}
						}
					});
				return view;
			}

		};
	}
}