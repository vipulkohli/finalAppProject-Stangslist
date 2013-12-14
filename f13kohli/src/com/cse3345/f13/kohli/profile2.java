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
public class profile2 extends Activity implements OnClickListener
{
	/** Called when the activity is first created. */
	Button openCam, mAvail,mSub;
	Button openWeb;
	LinearLayout bg;
	ImageView profilePicture;
	static final  int REQ = 5;
	WebView web;

	private ValueCallback<Uri> mUploadMessage;  
	private final static int FILECHOOSER_RESULTCODE=1;  

	@Override  
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {  
		if(requestCode==FILECHOOSER_RESULTCODE)  
		{  //found this from StackOverflow
			if (null == mUploadMessage) return;  
            Uri result = intent == null || resultCode != RESULT_OK ? null  
				: intent.getData();  
            mUploadMessage.onReceiveValue(result);  
            mUploadMessage = null;  
			openCam.setText(result.toString());
		}
		else{
//-----------I wrote this code below this line----------------------------------
			byte [] jpegData = intent.getExtras().getByteArray("image");
			openCam.setText("" + intent.getExtras().getByteArray("image").length);
			Bitmap img = BitmapFactory.decodeByteArray(jpegData, 0, jpegData.length);
			Drawable d = new BitmapDrawable(img);
			profilePicture.setBackground(d);
		}
	}
	@Override
	public void onClick(View photoButton)
	{
		if (photoButton.getId() == openWeb.getId())
			web.setVisibility(View.VISIBLE);
		else if(photoButton.getId() == mAvail.getId())
			startActivity(new Intent(this, CheckActivity.class));
		else if(photoButton.getId() == mSub.getId())
			finish();
		else
			startActivityForResult(new Intent(this, com.lightbox.android.camera.activities.Camera.class), REQ);
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);
		web = (WebView) findViewById(R.id.webView);
		profilePicture = (ImageView) findViewById(R.id.photo);
		openCam = (Button) findViewById(R.id.takePhoto);
		openWeb = (Button) findViewById(R.id.findFile);
		openCam.setOnClickListener(this);
		openWeb.setOnClickListener(this);
		mAvail = (Button) findViewById(R.id.editavail);
		mAvail.setOnClickListener(this);
		mSub = (Button) findViewById(R.id.submitbutton);
		mSub.setOnClickListener(this);
		web.getSettings().setJavaScriptEnabled(true);
		web.loadUrl("http://www.monkbananas.com/uploader/index.php");
//------Everything below this line wa found on StackOverflow--------------------
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
					profile2.this.startActivityForResult( Intent.createChooser( i, "File Chooser" ), FILECHOOSER_RESULTCODE );

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
