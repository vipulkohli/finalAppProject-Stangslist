package com.mycompany.myapp4;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.net.*;
import java.io.*;
import java.util.*;
import android.graphics.*;
import android.graphics.drawable.*;
import org.json.*;

public class MainActivity extends Activity
{
	Bitmap img = null;
	URL url;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
		try{
        super.onCreate(savedInstanceState);
		url = new URL("http://lyle.smu.edu/~craley/3345/img/smu-logo.png");
        setContentView(R.layout.main);
		img = BitmapFactory.decodeStream(url.openStream());
		Drawable d = new BitmapDrawable(img);
		ImageView iv = (ImageView) findViewById(R.id.imageView);
			LinearLayout can = (LinearLayout) findViewById(R.id.canvas);
		can.setBackgroundDrawable(d);
		    URL url = new URL("http://lyle.smu.edu/~craley/3345/docs/caricatures.json");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String str = "";
			String line = reader.readLine();
			while (line != null){
				str += line;
				line = reader.readLine();
			}
			TextView tv = (TextView) findViewById(R.id.json);
			tv.setText(str);
			
			
			
		}
		catch(MalformedURLException e){
			
		}
		catch(IOException e)
		{}
		catch(Exception e)
		{}
    }
	
}
