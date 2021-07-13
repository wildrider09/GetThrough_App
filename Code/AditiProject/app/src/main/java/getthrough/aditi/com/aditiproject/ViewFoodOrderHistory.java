package getthrough.aditi.com.aditiproject;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewFoodOrderHistory extends AppCompatActivity {
    ListView lv;
    List<String> order_id, order_amt, order_status,student_id;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_food_order_history);
        pd = new ProgressDialog(ViewFoodOrderHistory.this);
        pd.show();
        lv = (ListView) findViewById(R.id.lstorder);
        RequestQueue requestQueue = Volley.newRequestQueue(MyApplication.getInstance(), new HurlStack(null, ClientSSLSocketFactory.getSocketFactory()));
        StringRequest sr = new StringRequest(Request.Method.POST, "http://aditi.atwebpages.com/vieworders.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject job = new JSONObject(response);
                            JSONArray jar = job.getJSONArray("res");
                            order_status = new ArrayList<>();
                            order_id = new ArrayList<>();
                            order_amt = new ArrayList<>();
                            student_id=new ArrayList<>();
                            for (int i = 0; i < jar.length(); i++) {
                                JSONObject job2 = jar.getJSONObject(i);
                                order_id.add(job2.getString("order_id"));
                                order_amt.add(job2.getString("order_amt"));
                                order_status.add(job2.getString("order_status"));
                                student_id.add(job2.getString("student_id"));
                            }
                            pd.dismiss();
                            OrderHistoryAdapter adapter = new OrderHistoryAdapter(ViewFoodOrderHistory.this, order_id, order_amt, order_status,student_id);
                            lv.setAdapter(adapter);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.dismiss();
                        Toast.makeText(ViewFoodOrderHistory.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
                params.put("student_id",new SessionManager(ViewFoodOrderHistory.this).getstudentid());
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
