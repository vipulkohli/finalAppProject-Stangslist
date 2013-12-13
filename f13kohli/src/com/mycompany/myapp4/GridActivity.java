package com.mycompany.myapp4;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.AdapterView.*;
import android.widget.*;
import android.content.*;

public class GridActivity extends Activity
{
    Spinner mSpin;
	Spinner mCampSpin;
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
		mCampSpin = (Spinner) findViewById(R.id.campspin);
		final String [] campOps = {"Choose A Campus", "SMU Main Campus Dallas", "SMU-in-Plano", "SMU-in-Taos"};
		final String [] spinOps = {"Find by Category", "Find by Building"};
		String [] saleCats = {"Apps", "Books", "Calculators", "Laptops", "Tablets", "Furniture", "Phones", "Roommates", "Tutors"};
		String [] buildNames = {"All SMU", "All ResHalls", "Perkins", "Smith", "McElvaney", "Cockrell-McIntoch", "Greek", "Morrison-McGinnis","HTSC"};
		mGrid= (GridView) findViewById(R.id.grid);
		ArrayAdapter< String> ad = new ArrayAdapter < String > (this, android.R.layout.simple_spinner_item, spinOps);
		ArrayAdapter< String> spinCampusAdapter = new ArrayAdapter < String > (this, android.R.layout.simple_spinner_item, campOps);
		mCatsAdapter = new GridAdapter(saleCats);
		mBuildsAdapter = new GridAdapter(buildNames);
		mGrid.setAdapter(mCatsAdapter);
		mSpin.setAdapter(ad);
		mCampSpin.setAdapter(spinCampusAdapter);
		mSpin.setOnItemSelectedListener(spinListener);
    }
	private OnItemSelectedListener spinListener = new OnItemSelectedListener(){
		public void onItemSelected(AdapterView<?>parent, View v, int position, long id)
		{
			//Toast.makeText(getApplicationContext(), "hi", Toast.LENGTH_LONG);
			if (position > 0)
				mGrid.setAdapter(mBuildsAdapter);
			else
			    mGrid.setAdapter(mCatsAdapter);
		}
		public void onNothingSelected(AdapterView<?>parent)
		{

		}
	};
	public class GridAdapter extends BaseAdapter{
		private String[]gridWords;
		public GridAdapter(String[]inWords)
		{
			gridWords = inWords;
		}
		public int getCount(){
			return gridWords.length;
		}
		public Object getItem(int pos){
			return gridWords[pos];
		}
		public long getItemId(int position){
			return position;
		}
		public View getView(int position, View convertView, ViewGroup parent){
			LayoutInflater in = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
			View view = in.inflate(R.layout.grid_item, null);
			TextView tv = (TextView) view.findViewById(R.id.grid_item);
			ImageView iv = (ImageView) view.findViewById(R.id.iconLink);
			tv.setText(gridWords[position]);
			iv.setImageResource(R.drawable.ic_launcher);
			return view;
		}
	}
}
