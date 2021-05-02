package tw.idv.jameschen.jsonparser;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends Activity implements OnClickListener {
	// (1) 宣告屬性
	private Button btnGo;
	//
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// (2) 產生關聯
		btnGo = (Button) findViewById(R.id.btnGo);
		// (3) 註冊事件處理
		btnGo.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// Lab0702 - 使用  AsyncTask
		// https://datacenter.taichung.gov.tw/swagger/OpenData/34c2aa94-7924-40cc-96aa-b8d090f0ab69
		//
		DownloadAsyncTask dTask = new DownloadAsyncTask();
		dTask.execute("https://datacenter.taichung.gov.tw/swagger/OpenData/34c2aa94-7924-40cc-96aa-b8d090f0ab69", "...", "...");


	}

	// Lab0701
    class DownloadAsyncTask extends AsyncTask<String, Void, String> {

        @Override
		protected String doInBackground(String... urls) {
			//String url = urls[0];
			try {
				URL url = new URL(urls[0]);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				//connection.setRequestProperty("authentication", MainActivity.Authentication);
				connection.setDoInput(true);
				InputStream inputStream = connection.getInputStream();
				int status = connection.getResponseCode();
				Log.d("JSON parser", String.valueOf(status));
				String result = "";
				if (inputStream != null) {
					InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
					BufferedReader in = new BufferedReader(reader);
					String line = "";
					while ((line = in.readLine()) != null) {
						result += (line + "\n");
					}
				} else {
					result = "Did not work!";
				}
				connection.disconnect();
				return result;
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (ProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
        //

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("JSON:",s);
			try {
				JSONObject allData=new JSONObject(s);
				int status= allData.getInt("retCode");
				if(status==1){
					JSONObject allSites= allData.getJSONObject("retVal");
					Iterator<String> it= allSites.keys();
					ArrayList<String> siteNames=new ArrayList<>();

					while(it.hasNext()){
						String id =it.next();
						JSONObject site = allSites.getJSONObject(id);
						String sitename= site.getString("sna");
						int total=Integer.parseInt(site.getString("tot"));
						Log.i("JSON",id+":"+sitename+","+total);
						siteNames.add(sitename);
					}
					ListView lv = findViewById(R.id.lvsites);
					ArrayAdapter<String> adapter = new ArrayAdapter<>(
							MainActivity.this,
							android.R.layout.simple_list_item_1,
							siteNames
					);
					lv.setAdapter(adapter);

				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
    }
}
