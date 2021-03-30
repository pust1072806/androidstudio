package tw.edu.pu.s1072806.soho_h2;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class CheckoutActivity extends AppCompatActivity {

    private ImageView photo;
    private ImageButton btn_normalckeck,btn_cameracheck,btn_re,btn_ok;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        photo=findViewById(R.id.photo);
        btn_normalckeck=findViewById(R.id.btn_normalcheck);
        btn_cameracheck=findViewById(R.id.btn_cameracheck);

        btn_re=findViewById(R.id.btn_re);
        btn_ok=findViewById(R.id.btn_ok);
        btn_ok.setEnabled(false);
        btn_re.setEnabled(false);
        btn_cameracheck.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
                btn_ok.setEnabled(true);
                btn_re.setEnabled(true);
            }
        });
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it2 =new Intent();
                it2.setClass(CheckoutActivity.this,MainActivity.class);
                startActivity(it2);
            }
        });
        btn_re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

    }

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            photo.setImageBitmap(imageBitmap);
        }
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (scanningResult != null)
        {
            String scanContent = scanningResult.getContents();
            new AlertDialog.Builder(this)
                    .setTitle("『請確認…")
                    .setMessage("所掃到的訊息:"+scanContent.toString())
                    .setNegativeButton("確定",null)
                    .show();
            /*if(scanningResult.getContents() != null)
            {
                String scanContent = scanningResult.getContents();
                if (!scanContent.equals(""))
                {
                    //Toast.makeText(getApplicationContext(),"掃描內容: "+scanContent.toString(), Toast.LENGTH_LONG).show();
                    new AlertDialog.Builder(this)
                            .setTitle("『請確認…")
                            .setMessage("所掃到的訊息:"+scanContent.toString())
                            .setNegativeButton("確定",null)
                            .show();
                }
            }*/
        }else
        {

            //Toast.makeText(getApplicationContext(),"發生錯誤",Toast.LENGTH_LONG).show();
        }
    }


    public void onbuttonclick(View v)
    {
        View button1 = (View) findViewById(R.id.button);

        IntentIntegrator scanIntegrator = new IntentIntegrator(CheckoutActivity.this);
        scanIntegrator.setPrompt("請掃描");
        scanIntegrator.setTimeout(300000);
        scanIntegrator.setOrientationLocked(false);
        scanIntegrator.initiateScan();
    }





}
