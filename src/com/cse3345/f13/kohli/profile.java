package com.cse3345.f13.kohli;
import com.lightbox.android.camera.R;
import android.app.*;
import android.os.*;
import android.net.*;
import android.webkit.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.content.*;
public class profile extends Activity implements OnClickListener
{
	Button openCam;
	LinearLayout bg;
	ImageView profilePicture;
	static final  int REQ = 5;
    /** Called when the activity is first created. */
	WebView web;
	ProgressBar progressBar;

	private ValueCallback<Uri> mUploadMessage;  
	private final static int FILECHOOSER_RESULTCODE=1;  

	@Override
	public void onClick(View p1)
	{
		startActivityForResult(new Intent(this, com.lightbox.android.camera.activities.Camera.class), REQ);
	}
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
		profilePicture = (ImageView) findViewById(R.id.photo);
		openCam = (Button) findViewById(R.id.takePhoto);
		openCam.setOnClickListener(this);
		final WebView web = (WebView) findViewById(R.id.webView);
		web.getSettings().setJavaScriptEnabled(true);
		web.setWebViewClient(new WebViewClient(){

	//For Android 4.1
	public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture){
		mUploadMessage = uploadMsg;  
		Intent i = new Intent(Intent.ACTION_GET_CONTENT);  
		i.addCategory(Intent.CATEGORY_OPENABLE);  
		i.setType("image/*");  
		profile.this.startActivityForResult( Intent.createChooser( i, "File Chooser" ), profile.FILECHOOSER_RESULTCODE );

	}	
			public boolean shouldOverrideUrlLoading(WebView view, String url){
				web.loadUrl(url);
				return true;
			}
		});
		web.loadUrl("http://monkbananas.com/uploader/index.php");
    }


	protected void onActivityResult(int requestCode, int resultCode, Intent data){

		if(requestCode==FILECHOOSER_RESULTCODE)  
		{  
			if (null == mUploadMessage) return;  
            Uri result = data == null || resultCode != RESULT_OK ? null  
				: data.getData();  
            mUploadMessage.onReceiveValue(result);  
            mUploadMessage = null;  
		}
		else{
			byte [] jpegData = data.getExtras().getByteArray("image");
			openCam.setText(web.getUrl());
			Bitmap img = BitmapFactory.decodeByteArray(jpegData, 0, jpegData.length);
			Drawable d = new BitmapDrawable(img);
			profilePicture.setBackground(d);
		}
	}

}  

