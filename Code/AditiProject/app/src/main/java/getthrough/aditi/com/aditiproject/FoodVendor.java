package getthrough.aditi.com.aditiproject;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodVendor extends AppCompatActivity implements AdapterView.OnItemClickListener {
    SessionManager sm;
    ListView currentOrders;
    List<JSONObject> currentOrderObjects;
    String order_handle = " ", order_id;
    TextView galti_wala_text;
    VendorOrderHistoryAdapter vendorhistoryorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_food_vendor);
        galti_wala_text = (TextView) findViewById(R.id.galati_wala_text);
        sm = new SessionManager(FoodVendor.this);
        currentOrders = (ListView) findViewById(R.id.currentOrders);
        //Toast.makeText(this, sm.getstudentid(), Toast.LENGTH_SHORT).show();
        getAllPendingOrders();

    }

    private void getAllPendingOrders() {
        RequestQueue requestQueue = Volley.newRequestQueue(MyApplication.getInstance(), new HurlStack(null, ClientSSLSocketFactory.getSocketFactory()));
        StringRequest sr = new StringRequest(Request.Method.POST, "http://aditi.atwebpages.com/vieworders_vendor.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Toast.makeText(FoodVendor.this, response, Toast.LENGTH_SHORT).show();

                        if (response != null) {
                            try {
                                currentOrderObjects = new ArrayList<>();
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray jsonArray = jsonObject.getJSONArray("res");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    if (jsonArray.getJSONObject(i).getString("order_status").contains("pending")) {
                                        //currentOrderObjects.add(new JSONObject(response).getJSONArray("res"));
                                        currentOrderObjects.add(jsonArray.getJSONObject(i));
                                    }
                                }

                                if (currentOrderObjects.size() == 0) {
                                    currentOrders.setVisibility(View.GONE);
                                    galti_wala_text.setText("no orders pending");
                                } else {
                                    vendorhistoryorder = new VendorOrderHistoryAdapter(currentOrderObjects, FoodVendor.this);


                                    currentOrders.setAdapter(vendorhistoryorder);

                                    currentOrders.setOnItemClickListener(FoodVendor.this);
                                }

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.food_vendor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.order_vendor_history) {
            startActivity(new Intent(FoodVendor.this, Vendor_all_orders.class));
        } else if (item.getItemId() == R.id.vendor_food_logout) {

            sm.logout();
            startActivity(new Intent(FoodVendor.this, Login.class));
        }
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (!sm.isLoggedIn()) {
            finish();
        }

    }


    private void processOrder() {
        Toast.makeText(this, order_id + "\n" + order_handle, Toast.LENGTH_SHORT).show();
        RequestQueue requestQueue = Volley.newRequestQueue(MyApplication.getInstance(), new HurlStack(null, ClientSSLSocketFactory.getSocketFactory()));
        StringRequest sr = new StringRequest(Request.Method.POST, "http://aditi.atwebpages.com/processorder_vendor.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.contains("updated")) {
                            Toast.makeText(FoodVendor.this, response, Toast.LENGTH_SHORT).show();
                            getAllPendingOrders();
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
                params.put("order_handle", order_handle);
                params.put("order_id", order_id);
                return params;
            }
        };
        sr.setRetryPolicy(new DefaultRetryPolicy(
                20000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(sr);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        try {

            for (int i = 0; i < currentOrderObjects.size(); i++) {
                order_id = currentOrderObjects.get(i).getString("order_id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        PopupMenu popup = new PopupMenu(FoodVendor.this, currentOrders);
        //Inflating the Popup using xml file
        popup.getMenuInflater().inflate(R.menu.order_confirmation, popup.getMenu());

        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                //Toast.makeText(FoodVendor.this,"You Clicked : " + item.getTitle(),Toast.LENGTH_SHORT).show();


                switch (item.getItemId()) {
                    case R.id.order_dispatch:
                        order_handle = "dispatched";
                        processOrder();
                        return true;
                    case R.id.order_decline:
                        order_handle = "decline";
                        processOrder();
                        return true;

                }


                return true;
            }
        });

        popup.show();


    }
}
