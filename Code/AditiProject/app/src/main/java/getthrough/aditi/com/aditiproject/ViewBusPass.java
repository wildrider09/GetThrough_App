package getthrough.aditi.com.aditiproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.solver.widgets.Rectangle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gc.materialdesign.views.ButtonRectangle;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ViewBusPass extends AppCompatActivity {
    TextView passNo, stuName, faName, validTil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bus_pass);
        CardView passView = (CardView) findViewById(R.id.passView);
        CardView generateCard = (CardView) findViewById(R.id.generatecard);
        SessionManager sm = new SessionManager(ViewBusPass.this);
        passNo = (TextView) findViewById(R.id.passNo);
        stuName = (TextView) findViewById(R.id.stuName);
        faName = (TextView) findViewById(R.id.fathersName);
        validTil = (TextView) findViewById(R.id.validTill);
        if (sm.getPass().contains("not issued")) {
            generateCard.setVisibility(View.VISIBLE);
            passView.setVisibility(View.GONE);

        } else {
            setuppass();
            passView.setVisibility(View.VISIBLE);
            generateCard.setVisibility(View.GONE);
        }

       ButtonRectangle b = (ButtonRectangle) findViewById(R.id.generate);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewBusPass.this, GenerateBusPass.class);
                startActivity(i);
            }
        });
    }

    private void setuppass() {
        RequestQueue requestQueue = Volley.newRequestQueue(MyApplication.getInstance(), new HurlStack(null, ClientSSLSocketFactory.getSocketFactory()));
        StringRequest sr = new StringRequest(Request.Method.POST, "http://aditi.atwebpages.com/viewpass.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response != null) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray jsonArray = jsonObject.getJSONArray("res");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject job = jsonArray.getJSONObject(i);
                                    passNo.setText("pass No: "+job.getString("passno"));
                                    stuName.setText("student Name: "+job.getString("student_name"));
                                    faName.setText("Fathers Name: "+job.getString("fathers_name"));
                                    validTil.setText("Valid Till: "+job.getString("validity"));
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
                params.put("student_id", new SessionManager(ViewBusPass.this).getstudentid());
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
