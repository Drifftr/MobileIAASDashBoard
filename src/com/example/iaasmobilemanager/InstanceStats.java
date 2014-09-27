package com.example.iaasmobilemanager;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.comtools.JSONUtils;
import com.example.iaasmobilemanager.CreateInstance.Read;
import com.example.uitools.ExpandableListAdapterd;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

public class InstanceStats extends Activity {
	ExpandableListAdapterd listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    Context context;
    TextView instanceID,publicDNS,publicIP,running;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_instance_stats);
		final String stopURL="";
		final String rebootURL="";
		final String refreshURL="";
		final String terminateURL="";
		instanceID = (TextView) findViewById(R.id.lblInstanceID);
		publicDNS = (TextView) findViewById(R.id.lblPublicDNS);
		publicIP = (TextView) findViewById(R.id.lblPublicIP);
		running = (TextView) findViewById(R.id.lblRunning);
		Button btnReboot = (Button) findViewById(R.id.btnReboot);
		Button btnStop = (Button) findViewById(R.id.btnStop);
		Button btnTerminate = (Button) findViewById(R.id.btnTerminate);
		final String instanceID = getIntent().getStringExtra("instanceID");
		expListView = (ExpandableListView) findViewById(R.id.lvExp);
		context=this;
		//new Refresh().execute(refreshURL+"/"+instanceID);
		btnReboot.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	new Reboot().execute(rebootURL);
            }
        });
		btnStop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	new Stop().execute(stopURL+"/"+instanceID);
            }
        });
		btnTerminate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	new Terminate().execute(terminateURL+"/"+instanceID);
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.instance_stats, menu);
		return true;
	}
	public class Stop extends AsyncTask<String, Integer, String> {

    	@Override
    	protected void onPostExecute(String result) {
    		Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
    	}
    	
		@Override
		protected String doInBackground(String... arg0) {
			JSONUtils ju=new JSONUtils();
			String s=ju.getPOSTJSONFromUrl(arg0[0]);
			return s;
		}}
	public class Reboot extends AsyncTask<String, Integer, String> {

    	@Override
    	protected void onPostExecute(String result) {
    		Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
    	}
    	
		@Override
		protected String doInBackground(String... arg0) {
			
			JSONUtils ju=new JSONUtils();
			String s=ju.getPOSTJSONFromUrl(arg0[0]);
			return s;
		}}
	public class Terminate extends AsyncTask<String, Integer, String> {

    	@Override
    	protected void onPostExecute(String result) {
    		Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
    	}
    	
		@Override
		protected String doInBackground(String... arg0) {
			
			JSONUtils ju=new JSONUtils();
			String s=ju.getPOSTJSONFromUrl(arg0[0]);
			return s;
		}}
	
	public class Refresh extends AsyncTask<String, Integer, String> {

    	@Override
    	protected void onPostExecute(String result) {
    		Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
    		listDataHeader = new ArrayList<String>();
            listDataChild = new HashMap<String, List<String>>();
     
            // Adding child data
            listDataHeader.add("Basic");
            listDataHeader.add("Hardware");
            listDataHeader.add("OS");
            listDataHeader.add("Location");
     
            // Adding child data
            List<String> basic = new ArrayList<String>();
            basic.add("The Shawshank Redemption");
            basic.add("The Godfather");
            basic.add("The Godfather: Part II");
            basic.add("Pulp Fiction");
            basic.add("The Good, the Bad and the Ugly");
            basic.add("The Dark Knight");
            basic.add("12 Angry Men");
     
            List<String> hardware = new ArrayList<String>();
            hardware.add("The Conjuring");
            hardware.add("Despicable Me 2");
            hardware.add("Turbo");
            hardware.add("Grown Ups 2");
            hardware.add("Red 2");
            hardware.add("The Wolverine");
     
            List<String> os = new ArrayList<String>();
            os.add("2 Guns");
            os.add("The Smurfs 2");
            os.add("The Spectacular Now");
            os.add("The Canyons");
            os.add("Europa Report");
            
            List<String> location = new ArrayList<String>();
            location.add("2 Guns");
            location.add("The Smurfs 2");
            location.add("The Spectacular Now");
            location.add("The Canyons");
            location.add("Europa Report");
     
            listDataChild.put(listDataHeader.get(0), basic); // Header, Child data
            listDataChild.put(listDataHeader.get(1), hardware);
            listDataChild.put(listDataHeader.get(2), os);
            listDataChild.put(listDataHeader.get(2), location);
    		listAdapter = new ExpandableListAdapterd(context, listDataHeader, listDataChild);
    		expListView.setAdapter(listAdapter);
    	}
    	
		@Override
		protected String doInBackground(String... arg0) {
			
			JSONUtils ju=new JSONUtils();
			String s=ju.getPOSTJSONFromUrl(arg0[0]);
			return s;
		}}

}
