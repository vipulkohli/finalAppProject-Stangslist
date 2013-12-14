package com.lightbox.android.camera.activities;
import com.lightbox.android.camera.R;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.content.*;
public class MainActivity extends Activity implements OnClickListener
{
	Button openCam;
	LinearLayout bg;
	ImageView profilePicture;
	static final  int REQ = 5;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		profilePicture = (ImageView) findViewById(R.id.photo);
		openCam = (Button) findViewById(R.id.takePhoto);
		openCam.setOnClickListener(this);
    }

	@Override
	public void onClick(View p1)
	{
		startActivityForResult(new Intent(this, Camera.class), REQ);
	}
	protected void onActivityResult(int a, int b, Intent data){
		byte [] jpegData = data.getExtras().getByteArray("image");
		openCam.setText("" + data.getExtras().getByteArray("image").length);
		Bitmap img = BitmapFactory.decodeByteArray(jpegData, 0, jpegData.length);
		Drawable d = new BitmapDrawable(img);
		profilePicture.setBackground(d);
	}

}  
