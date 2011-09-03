package jp.example.android.firstproject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import android.app.Activity;
import android.app.Application;
import android.app.WallpaperManager;
import android.os.Bundle;
import android.util.Log;
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
				
				try {
					URL url = new URL("http://yusa.imouto.org/sample/413a5a793d06d4aae93fbca4c51965ca/moe%20192233%20sample.jpg");
				    InputStream urlst = url.openStream();
				    FileOutputStream fo = openFileOutput("file.jpg", MODE_PRIVATE);
//				    int r;
//				    while (0 <= (r = urlst.read())){
//				    	fo.write(r);
//				    }
//				    urlst.reset();
				    WallpaperManager walman = WallpaperManager.getInstance(getApplicationContext());
				    //walman.suggestDesiredDimensions(640, 640);
				    walman.setStream(urlst);
				    
				    fo.flush();
				    fo.close();
				    urlst.close();
				    
				} catch (Exception e){
					Log.d(getString(R.string.app_name), e.getMessage());
					File f = new File("file.jpg");
					f.delete();
				}
				Toast.makeText(getApplicationContext(), "Downloaded!!", Toast.LENGTH_LONG).show();				    
				
			}
		});
        
    }
}