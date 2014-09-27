package com.example.iaasmobilemanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.comtools.JSONUtils;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_LONG).show();
            	new Read().execute("");
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private String getData(){
    	
    	TextView test = (TextView) findViewById(R.id.textView1);
    	test.setText("testing");
    	HttpClient httpClient = new DefaultHttpClient();
    	HttpGet request = new HttpGet("http://192.168.43.44:9763/JaxOrderProcess_1.0.0/services/service");
    	//request.addHeader("Accept", "text/html");
    	Toast.makeText(getApplicationContext(), "msg msg", Toast.LENGTH_SHORT).show();
    	StringBuilder sb =  new StringBuilder();
    	try {
			HttpResponse response = httpClient.execute(request);
			HttpEntity entity = response.getEntity();
			InputStream instream = entity.getContent();
			
			BufferedReader r = new BufferedReader(new InputStreamReader(instream));	
			for (String line = r.readLine(); line != null; line = r.readLine()) {
	             sb.append(line);	
				}
			String jaxrsmessage = sb.toString();		
			instream.close();
			test.setText(jaxrsmessage);
		
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return sb.toString();
    }
    
    public class Read extends AsyncTask<String, Integer, String> {

    	@Override
    	protected void onPostExecute(String result) {
    		Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
    	}
    	
		@Override
		protected String doInBackground(String... arg0) {
			JSONUtils ju=new JSONUtils();
			String s=ju.getJSONFromUrl("http://192.168.43.44:9763/JaxOrderProcess_1.0.0/services/service");
			return s;
		}}
}
