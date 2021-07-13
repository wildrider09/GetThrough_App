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
import java.util.List;

public class Food extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView food_list;
    List<JSONObject> job1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_food);
        food_list = (ListView) findViewById(R.id.food_list);
        setUpfood();
    }

    private void setUpfood() {
        RequestQueue requestQueue = Volley.newRequestQueue(MyApplication.getInstance(), new HurlStack(null, ClientSSLSocketFactory.getSocketFactory()));
        StringRequest sr = new StringRequest(Request.Method.GET, "http://aditi.atwebpages.com/getalltruckdata.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Toast.makeText(Food.this, response, Toast.LENGTH_SHORT).show();
                        try {
                            job1 = new ArrayList<>();
                            JSONObject job = new JSONObject(response);
                            JSONArray jsonArray = job.getJSONArray("res");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                job1.add(jsonArray.getJSONObject(i));
                            }
                            MainFoodAdapter adapter = new MainFoodAdapter(Food.this, job1);
                            food_list.setAdapter(adapter);
                            food_list.setOnItemClickListener(Food.this);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        sr.setRetryPolicy(new DefaultRetryPolicy(
                20000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(sr);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.orderhistory, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.orderHistory) {
            startActivity(new Intent(Food.this, ViewFoodOrderHistory.class));
        }
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(Food.this, FoodDetails.class);
        i.putExtra("data", String.valueOf(job1.get(position)));


        startActivity(i);




}
}
