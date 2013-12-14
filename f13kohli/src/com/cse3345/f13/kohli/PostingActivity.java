package com.cse3345.f13.kohli;
import com.lightbox.android.camera.R;
import android.app.*;
import android.os.*;
import android.net.*;
import android.webkit.*;
import android.content.res.*; 
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.content.*;
import java.util.*;
import android.widget.AdapterView.*;
public class PostingActivity extends Activity implements OnClickListener
{
	/** Called when the activity is first created. */
	private Button openCam, mAvail, mSub, openWeb, mBack;
	private TextView profilePicture;
	private byte [] jpegData;
	private Spinner mCatSpin;
	private String mChosenCategory;
	private EditText mTitle, mLocation, mPrice, mDesc;
	static final int REQ = 5;
	private WebView web;
	private CheckBox mCard, mCheck, mOnline, mCash;
    private ArrayList<String>payment;
	private ValueCallback<Uri> mUploadMessage;  
	private final static int FILECHOOSER_RESULTCODE=1;  

	@Override  
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {  
		if(requestCode==FILECHOOSER_RESULTCODE)  
		{  //found this from StackOverflow
			if (null == mUploadMessage) return;  
            Uri result = intent == null || resultCode != RESULT_OK ? null  : intent.getData();  
            mUploadMessage.onReceiveValue(result);  
            mUploadMessage = null;  
		}
		else{
//-----------I wrote this code below this line----------------------------------
			jpegData = intent.getExtras().getByteArray("image");
			Bitmap img = BitmapFactory.decodeByteArray(jpegData, 0, jpegData.length);
			Drawable d = new BitmapDrawable(img);
			profilePicture.setBackground(d);
		}
	}
	@Override
	public void onClick(View inButton)
	{
		boolean isErr = false;
		if (mTitle.getText().toString().length() == 0){
			mTitle.setError("Required");
			mTitle.setEms(10);
			isErr = true;
		}
		if(mDesc.getText().toString().length() == 0){
			mDesc.setActivated(true);
			mDesc.setError("Required");
			isErr = true;
		}
		if (inButton.getId() == openWeb.getId())
			web.setVisibility(View.VISIBLE);
		else if(inButton.getId() == mAvail.getId())
			startActivity(new Intent(this, CheckActivity.class));
		else if(inButton.getId() == mBack.getId())
			finish();
		else if(inButton.getId() == mSub.getId()){
			AlertDialog.Builder al = new AlertDialog.Builder(this);
			if(isErr)
				return;
			else
			  al.setTitle("Continue?")
				.setIcon(R.drawable.ornament)
				.setMessage("Your listing is going to be submitted to your chosen category.")
				.setPositiveButton("OK", new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface d, int x){	
						payment = new ArrayList<String>();
						if(mCard.isChecked())
							payment.add("Card");
						if(mCheck.isChecked())
							payment.add("Check");
						if(mOnline.isChecked())
						    payment.add("Online");
					    if(mCash.isChecked())
							payment.add("Cash");
//			Toast.makeText(getApplicationContext(), payment.toString(), Toast.LENGTH_LONG).show();
						Intent intent = new Intent(getApplicationContext(), StartActivity.class);
						intent.putExtra("Payment", payment);
						intent.putExtra("Category", mChosenCategory); 
						intent.putExtra("Title", mTitle.getText().toString());
						intent.putExtra("Price", mPrice.getText().toString());
						intent.putExtra("Description", mDesc.getText().toString());
						intent.putExtra("Location", mLocation.getText().toString());
						intent.putExtra("Photo", jpegData);
						startActivity(intent);
				}
			}).setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface d, int x){
					}
			}).show();
		}
		else
			startActivityForResult(new Intent(this, com.lightbox.android.camera.activities.Camera.class), REQ);
	}
	
	public void findViews(){
		web = (WebView) findViewById(R.id.webView);
		profilePicture = (TextView) findViewById(R.id.photo);
		mTitle = (EditText) findViewById(R.id.title);
		mLocation = (EditText) findViewById(R.id.location);
		mCard = (CheckBox)findViewById(R.id.card);
		mOnline = (CheckBox)findViewById(R.id.online);
		mCheck = (CheckBox)findViewById(R.id.cheque);
		mCash = (CheckBox)findViewById(R.id.cash);
		openCam = (Button) findViewById(R.id.takePhoto);
		openWeb = (Button) findViewById(R.id.findFile);
		mPrice = (EditText) findViewById(R.id.price);
		mCatSpin = (Spinner) findViewById(R.id.catspin);
		mDesc = (EditText) findViewById(R.id.desc);
		mBack = (Button) findViewById(R.id.backbutton);
	}
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);
		findViews();
		/*Select Category work */
		final String [] categories = getIntent().getStringArrayExtra("Options");
		ArrayAdapter< String> catadapt;
		catadapt= new ArrayAdapter<String> (this, android.R.layout.simple_spinner_item, categories);
		mCatSpin.setAdapter(catadapt);
		mCatSpin.setOnItemSelectedListener(new OnItemSelectedListener(){
			public void onItemSelected(AdapterView<?>parent, View v, int pos, long id){
					mChosenCategory = categories[pos];
			}
			public void onNothingSelected(AdapterView<?>parent){
				
			}
		});
		/*Availability selection work*/		
		mAvail = (Button) findViewById(R.id.editavail);
		mAvail.setOnClickListener(this);
		/*Submit/Back button work*/
		mSub = (Button) findViewById(R.id.submitbutton);
		mSub.setOnClickListener(this);
		mBack.setOnClickListener(this);
		/*Web View & Camera uploader work*/
		openCam.setOnClickListener(this);
		openWeb.setOnClickListener(this);		
		web.getSettings().setJavaScriptEnabled(true);
		web.loadUrl("http://www.monkbananas.com/uploader/index.php");
//------Everything below this line was found on StackOverflow--------------------
		web.setWebChromeClient(new WebChromeClient()  
			{  
				@Override
				public boolean shouldOverrideUrlLoading(WebView v, String url){
					web.loadUrl(url);
					return true;
				}
				//The undocumented magic method override  
				//Eclipse will swear at you if you try to put @Override here  
				// For Android 3.0+
				public void openFileChooser(ValueCallback<Uri> uploadMsg) {  

					mUploadMessage = uploadMsg;  
					Intent i = new Intent(Intent.ACTION_GET_CONTENT);  
					i.addCategory(Intent.CATEGORY_OPENABLE);  
					i.setType("image/*");  
					startActivityForResult(Intent.createChooser(i,"File Chooser"), FILECHOOSER_RESULTCODE);  

				}

				// For Android 3.0+
				public void openFileChooser( ValueCallback uploadMsg, String acceptType ) {
					mUploadMessage = uploadMsg;
					Intent i = new Intent(Intent.ACTION_GET_CONTENT);
					i.addCategory(Intent.CATEGORY_OPENABLE);
					i.setType("*/*");
					startActivityForResult(
						Intent.createChooser(i, "File Browser"),
						FILECHOOSER_RESULTCODE);
				}

				//For Android 4.1
				public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture){
					mUploadMessage = uploadMsg;  
					Intent i = new Intent(Intent.ACTION_GET_CONTENT);  
					i.addCategory(Intent.CATEGORY_OPENABLE);  
					i.setType("image/*");  
					startActivityForResult( Intent.createChooser( i, "File Chooser" ), FILECHOOSER_RESULTCODE );

				}

			});  


		}
	
	public class myWebClient extends WebViewClient
	{
		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			// TODO Auto-generated method stub
			super.onPageStarted(view, url, favicon);
		}

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			// TODO Auto-generated method stub

			view.loadUrl(url);
			return true;

		}

		@Override
		public void onPageFinished(WebView view, String url) {
			// TODO Auto-generated method stub
			super.onPageFinished(view, url);
		}
	}

//flipscreen not loading again
	@Override
	public void onConfigurationChanged(Configuration newConfig){        
		super.onConfigurationChanged(newConfig);
	}

// To handle "Back" key press event for WebView to go back to previous screen.
	/*@Override
	 public boolean onKeyDown(int keyCode, KeyEvent event) 
	 {
	 if ((keyCode == KeyEvent.KEYCODE_BACK) && web.canGoBack()) {
	 web.goBack();
	 return true;
	 }
	 return super.onKeyDown(keyCode, event);
	 }*/
	}
