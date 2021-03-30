package tw.edu.pu.s1072806.soho_h2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {
    private ImageButton btn_checkin;
    private ImageButton btn_checkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_checkout=(ImageButton)findViewById(R.id.btn_checkout);
        btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it2 =new Intent();
                it2.setClass(MainActivity.this,CheckoutActivity.class);
                startActivity(it2);
            }
        });

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate (R.menu.optionmenu,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId ()){
            case R.id.call:
                //////可利用 Intent 撥打學校總機(04-26328001)
                Uri dial1=Uri.parse("tel:04-26328001");
                Intent it =new Intent(Intent.ACTION_DIAL, dial1);
                startActivity(it);
                break;
            case  R.id.open:
                /////，可利用 Intent 開啟學校官網(http://www.pu.edu.tw)
                Uri web1=Uri.parse("http://www.pu.edu.tw");
                Intent it2=new Intent(Intent.ACTION_VIEW,web1);
                startActivity(it2);
                break;
        }
        return super.onOptionsItemSelected (item);
    }

    public void onbuttonclick(View v)
    {
        View button1 = (View) findViewById(R.id.button);

        IntentIntegrator scanIntegrator = new IntentIntegrator(MainActivity.this);
        scanIntegrator.setPrompt("請掃描");
        scanIntegrator.setTimeout(300000);
        scanIntegrator.setOrientationLocked(false);
        scanIntegrator.initiateScan();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null)
        {
            String scanContent = scanningResult.getContents();
            new AlertDialog.Builder(this)
                    .setTitle("『請確認…")
                    .setMessage("所掃到的訊息:"+scanContent.toString())
                    .setNegativeButton("確定",null)
                    .show();

        }
        else
        {
            super.onActivityResult(requestCode, resultCode, intent);
            Toast.makeText(getApplicationContext(),"發生錯誤",Toast.LENGTH_LONG).show();
        }

    }

}