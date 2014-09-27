package com.example.iaasmobilemanager;

import com.example.comtools.JSONUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateInstance extends Activity {
	String groupName;
	String OS;
	String OSVersion;
	String ram;
	String count;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_instance);
		Button createNew = (Button) findViewById(R.id.btnCreate);
		final EditText eleGroupName = (EditText) findViewById(R.id.txtGroupName);
		final EditText eleOS = (EditText) findViewById(R.id.txtOS);
		final EditText eleOSVersion = (EditText) findViewById(R.id.txtOSVersion);
		final EditText eleRam = (EditText) findViewById(R.id.txtRam);
		final EditText eleCount = (EditText) findViewById(R.id.txtCount);
		
    	createNew.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	groupName=eleGroupName.getText().toString();
            	OS=eleOS.getText().toString();
            	OSVersion=eleOSVersion.getText().toString();
            	ram=eleRam.getText().toString();
            	count=eleCount.getText().toString();
            	new Read().execute(groupName,OS,OSVersion,ram,count);
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.create_instance, menu);
		return true;
	}

	public class Read extends AsyncTask<String, Integer, String> {

    	@Override
    	protected void onPostExecute(String result) {
    		Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
    	}
    	
		@Override
		protected String doInBackground(String... arg0) {
			String baseURL="";
			JSONUtils ju=new JSONUtils();
			String s=ju.getPOSTJSONFromUrl(baseURL+"?"+arg0[0]+"&"+arg0[1]+"&"+arg0[1]+"&"+arg0[2]+"&"+arg0[3]+"&"+arg0[4]);
			return s;
		}}
}
