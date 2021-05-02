package tw.idv.jameschen.jsonparser;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

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

	}

	// Lab0701
    class DownloadAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }
        //

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}
