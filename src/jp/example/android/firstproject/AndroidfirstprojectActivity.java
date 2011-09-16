package jp.example.android.firstproject;

import jp.example.android.firstproject.RecievedActivity;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class AndroidfirstprojectActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
        	@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "clicked", Toast.LENGTH_LONG).show();
				/*
			    BitmapFactory.Options opts = new Options();
			    opts.inPurgeable = true;
			    opts.inSampleSize = 3;
			    
			    Bitmap obmp = BitmapFactory.decodeResource(getResources(), R.drawable.large, opts);
			    WallpaperManager walman = WallpaperManager.getInstance(getApplicationContext());
			    //walman.suggestDesiredDimensions(640, 640);
			    try{
			    walman.setBitmap(obmp);
			    }catch(IOException e){
			    	Log.d("test", e.getMessage());
			    }
				new GetImageFileTask().execute("http://yusa.imouto.org/image/b92803b1959999ec2ef4cfa3de80fa99/moe%20192448%20mitha%20seifuku%20thighhighs.jpg","http://yusa.imouto.org/sample/df736b7cadb485bfeafbfc113a53e07c/moe%20192104%20sample.jpg");
				*/
//				try {
//					URL url = new URL("http://kyamon.com/wp-content/uploads/2011/07/google_logo.jpg");
//				    InputStream urlst = url.openStream();
//				    FileOutputStream fo = openFileOutput("file.jpg", MODE_PRIVATE);
////				    int r;
////				    while (0 <= (r = urlst.read())){
////				    	fo.write(r);
////				    }
////				    urlst.reset();
//				    WallpaperManager walman = WallpaperManager.getInstance(getApplicationContext());
//				    //walman.suggestDesiredDimensions(640, 640);
//				    walman.setStream(urlst);
//				    
//				    fo.flush();
//				    fo.close();
//				    urlst.close();
//				    
//				} catch (Exception e){
//					Log.d(getString(R.string.app_name), e.getMessage());
//					File f = new File("file.jpg");
//					f.delete();
//				}
//	
		    	Intent i = new Intent(getApplicationContext(), RecievedActivity.class);
		    	PendingIntent sender = PendingIntent.getBroadcast(AndroidfirstprojectActivity.this, 0, i, 0);
		    	AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
//		    	am.setRepeating(AlarmManager.RTC, System.currentTimeMillis(), 5000, sender);
		    	am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+10000, sender);
			}
		});
        
    }
    	
    class GetImageFileTask extends AsyncTask<String, Void, Boolean>{
    	private Exception exception;

    	@Override
    	protected Boolean doInBackground(String... vurl) {
    		try{
    			URL url = new URL(vurl[0]);
    			InputStream is = url.openStream();
			    WallpaperManager walman = WallpaperManager.getInstance(getApplicationContext());	  
//			    walman.setStream(is);

			    DisplayMetrics metric = new DisplayMetrics();
			    ((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(metric);
			    int dsize = Math.max(metric.heightPixels, metric.widthPixels);			   
			    float scale = 1.0F;	
			    BitmapFactory.Options opts = new Options();
			    opts.inPurgeable = true;
			    
			    Bitmap obmp = BitmapFactory.decodeStream(is,null,opts);
			    scale = dsize / (float)Math.min(obmp.getWidth(), obmp.getHeight());
			    obmp = Bitmap.createScaledBitmap(obmp, (int)(scale*obmp.getWidth()), (int)(scale*obmp.getHeight()), true);
			    walman.setBitmap(obmp);

    		} catch(IOException e){
    			this.exception = e;
    			Log.d(getString(R.string.app_name), e.getMessage());
    			return false;
    		}
    		
    		return true;
		}
    	protected void onPostExecute(Boolean finished){
    		if (finished){
    			Toast.makeText(getApplicationContext(), "Downloaded!!", Toast.LENGTH_LONG).show();
    		}else{
    			Toast.makeText(getApplicationContext(), "Download failed", Toast.LENGTH_LONG).show();
    		}
    		return;
    	}
    }
}