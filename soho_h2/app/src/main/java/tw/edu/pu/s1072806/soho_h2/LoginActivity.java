package tw.edu.pu.s1072806.soho_h2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginActivity  extends AppCompatActivity {
    private Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        btn_login=(Button)findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent  it = new Intent();
                it.setClass(LoginActivity.this,MainActivity.class);
                startActivity(it);
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


}
