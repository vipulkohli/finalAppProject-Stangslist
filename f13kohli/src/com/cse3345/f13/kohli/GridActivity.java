package com.cse3345.f13.kohli;
import com.lightbox.android.camera.R;
import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.AdapterView.*;
import android.widget.*;
import android.content.*;
import android.view.View.*;
public class GridActivity extends Activity
{
    Spinner mSpin;
	Spinner mCampSpin;
	GridAdapter mCatsAdapter;
	GridAdapter mBuildsAdapter;
	GridView mGrid;
	/** Called when the activity is first created. */

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		super.onCreateOptionsMenu(menu);
		MenuInflater in = getMenuInflater();
		in.inflate(R.menu.vipul, menu);
		return true;

	}
/*
	@Override
	public void onClick(View p1)
	{
		mSpin.setOnItemSelectedListener(spinListener);
	}
*/	
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid);
		mSpin = (Spinner) findViewById(R.id.spin);
		mCampSpin = (Spinner) findViewById(R.id.campspin);
		final String [] campOps = {"Choose A Campus", "SMU Main Campus Dallas", "SMU-in-Plano", "SMU-in-Taos"};
		final String [] spinOps = {"Find by Category", "Find by Building"};
		Intent i = getIntent();
		
		mGrid= (GridView) findViewById(R.id.grid);
		ArrayAdapter< String> ad = new ArrayAdapter < String > (this, android.R.layout.simple_spinner_item, spinOps);
		ArrayAdapter< String> spinCampusAdapter = new ArrayAdapter < String > (this, android.R.layout.simple_spinner_item, campOps);
		final String [] options = setGridAdapter(i.getIntExtra("Topic",0));
		mSpin.setAdapter(ad);
		mCampSpin.setAdapter(spinCampusAdapter);
		Button post = (Button) findViewById(R.id.listitem);
		post.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				startActivity(new Intent(getApplicationContext(), PostingActivity.class).putExtra("Options", options));
			}
		});
		
    }
	private String [] setGridAdapter(int topic){
		String [] saleCats = {"Apps", "Books", "Calculators", "Laptops", "Tablets", "Furniture", "Phones", "Roommates", "Tutors"};
		String [] buildNames = {"All SMU", "All ResHalls", "Perkins", "Smith", "McElvaney", "Cockrell-McIntoch", "Greek", "Morrison-McGinnis","HTSC"};
		String [] places = {"Airports", "Car Rental", "Housing", "Restaurants", "Gas Stations"};
		switch (topic){
		case 1:
			mGrid.setAdapter(new GridAdapter(buildNames));
			return buildNames;
		case 2:
				mGrid.setAdapter(new GridAdapter(places));
				return places;
		default:
				mGrid.setAdapter(new GridAdapter(saleCats));
				return saleCats;
		}
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
			final TextView tv = (TextView) view.findViewById(R.id.grid_item);
			ImageView iv = (ImageView) view.findViewById(R.id.iconLink);
		    tv.setText(gridWords[position]);
			iv.setImageResource(R.drawable.ic_launcher);
			final String sendCategory = gridWords[position];
			OnClickListener viewPostings = new OnClickListener(){
				public void onClick(View v){
					Intent i = new Intent(getApplicationContext(), StartActivity.class);
					i.putExtra("Category", sendCategory);
					startActivity(i);
				}
			};
			iv.setOnClickListener(viewPostings);
			tv.setOnClickListener(viewPostings);
			return view;
		}
	}
}
