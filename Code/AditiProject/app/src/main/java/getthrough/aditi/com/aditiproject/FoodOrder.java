package getthrough.aditi.com.aditiproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodOrder extends AppCompatActivity {
    List<String> llisst;
    ListView lv;
    Button confirmOrder;
    String items = "", prices = "",truck_id=" ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_order);
        lv = (ListView) findViewById(R.id.finalorder);
        truck_id=getIntent().getExtras().getString("truck_id");
        confirmOrder = (Button) findViewById(R.id.confirm_order);
        String dataitems = getIntent().getExtras().getString("dataitems");
        String itarr[] = dataitems.split(",");
        llisst = new ArrayList<>();
        for (int i = 0; i < itarr.length; i++) {
            if (i != itarr.length - 1) {
                if (i % 2 == 0) {
                    items = items + itarr[i] + ",";
                } else {
                    prices = prices + itarr[i] + ",";
                }
            } else {
                if (i % 2 == 0) {
                    items = items + itarr[i];
                } else {
                    prices = prices + itarr[i];
                }
            }


            llisst.add(itarr[i]);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, llisst);
        lv.setAdapter(adapter);
        confirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestQueue requestQueue = Volley.newRequestQueue(MyApplication.getInstance(), new HurlStack(null, ClientSSLSocketFactory.getSocketFactory()));
                StringRequest sr = new StringRequest(Request.Method.POST, "http://aditi.atwebpages.com/neworder.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.contains("order placed")) {
                                    startActivity(new Intent(FoodOrder.this, Food.class));
                                }
                                Toast.makeText(FoodOrder.this, response, Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("prices", prices);
                        params.put("items", items);
                        params.put("truck_id",truck_id);
                        params.put("student_id", new SessionManager(FoodOrder.this).getstudentid());
                        return params;
                    }
                };
                sr.setRetryPolicy(new DefaultRetryPolicy(
                        20000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                requestQueue.add(sr);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
