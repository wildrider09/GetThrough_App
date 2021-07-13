package getthrough.aditi.com.aditiproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vendor_all_orders extends AppCompatActivity {
    SessionManager sm;
    ListView currentOrders;
    List<JSONObject> currentOrderObjects;


    VendorOrderHistoryAdapter vendorhistoryorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_vendor_all_orders);
        sm = new SessionManager(Vendor_all_orders.this);
        currentOrders = (ListView) findViewById(R.id.list_orders_vendor_all);
        getAllPendingOrders();
    }

    private void getAllPendingOrders() {
        RequestQueue requestQueue = Volley.newRequestQueue(MyApplication.getInstance(), new HurlStack(null, ClientSSLSocketFactory.getSocketFactory()));
        StringRequest sr = new StringRequest(Request.Method.POST, "http://aditi.atwebpages.com/vieworders_vendor.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(Vendor_all_orders.this, response, Toast.LENGTH_SHORT).show();

                        if (response != null) {
                            try {
                                currentOrderObjects = new ArrayList<>();
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray jsonArray = jsonObject.getJSONArray("res");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    //if (jsonArray.getJSONObject(i).getString("order_status").contains("pending")) {
                                        //currentOrderObjects.add(new JSONObject(response).getJSONArray("res"));
                                        currentOrderObjects.add(jsonArray.getJSONObject(i));
                                   // }
                                }    // currentOrders.setOnItemClickListener(Vendor_all_orders.this);
                                vendorhistoryorder = new VendorOrderHistoryAdapter(currentOrderObjects, Vendor_all_orders.this);


                                currentOrders.setAdapter(vendorhistoryorder);



                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
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
                params.put("vendor_id", sm.getstudentid());
                return params;
            }
        };
        sr.setRetryPolicy(new DefaultRetryPolicy(
                20000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(sr);
    }

}
