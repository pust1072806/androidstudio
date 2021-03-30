package tw.edu.pu.s1072806.soho_h2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class Order_Activity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView lvorders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        lvorders=findViewById(R.id.lvorders);
        lvorders.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String [] orders=getResources().getStringArray(R.array.orders);
        new AlertDialog.Builder(this)
                .setTitle("『請確認…")
                .setMessage("你選擇的餐盒是:"+orders[position])
                .setPositiveButton("正確",null)
                .setNegativeButton("重選",null)
                .show();
    }
}
