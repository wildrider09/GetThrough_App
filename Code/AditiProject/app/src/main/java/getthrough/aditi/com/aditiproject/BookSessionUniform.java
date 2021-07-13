package getthrough.aditi.com.aditiproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class BookSessionUniform extends AppCompatActivity {
    EditText yearText, numberText, dateText;
    Spinner slotText;

    Button accept;


    /*$student_id=$_REQUEST['student_id'];
    $year=$_REQUEST['year'];
    $date=$_REQUEST['date'];
    $slot=$_REQUEST['slot'];
    $number=$_REQUEST['number'];*/

    String year, date, slot, number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_book_session_uniform);


        String status = new SessionManager(BookSessionUniform.this).getUniform();

        yearText = (EditText) findViewById(R.id.yearText);
        numberText = (EditText) findViewById(R.id.numberText);
        dateText = (EditText) findViewById(R.id.dateText);
        slotText = (Spinner) findViewById(R.id.slotText);
        accept = (Button) findViewById(R.id.button);

        final List<String> slots = new ArrayList<>();
        slots.add("morning slots");
        slots.add("afternoon slots");
        slots.add("evening slots");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(BookSessionUniform.this, android.R.layout.simple_spinner_item, slots);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        slotText.setAdapter(adapter);
        slotText.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                slot = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year = yearText.getText().toString();
                date = dateText.getText().toString();
                number = numberText.getText().toString();
                //                if (slot != null && new SessionManager(BookSessionUniform.this).getUniform().contains(" ")) {
                    bookSession();
                //} else if (slot != null) {
                //    Toast.makeText(BookSessionUniform.this, "select a time slot", Toast.LENGTH_SHORT).show();
                //} else {
                 //   Toast.makeText(BookSessionUniform.this, "already booked a session hence you need to wait for your turn", Toast.LENGTH_SHORT).show();
                //}
            }
        });
    }

    private void bookSession() {


        RequestQueue requestQueue = Volley.newRequestQueue(MyApplication.getInstance(), new HurlStack(null, ClientSSLSocketFactory.getSocketFactory()));
        StringRequest sr = new StringRequest(Request.Method.POST, "http://aditi.atwebpages.com/bookuniformsession.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                            SessionManager sm=new SessionManager(BookSessionUniform.this);
                            sm.setUniform(response);
                            Toast.makeText(BookSessionUniform.this, "session booked", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(BookSessionUniform.this,Home.class));

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
                params.put("year", year);
                params.put("slot", slot);
                params.put("date", date);
                params.put("number", number);
                params.put("student_id", new SessionManager(BookSessionUniform.this).getstudentid());
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
    protected void onPause() {
        super.onPause();
        finish();
    }
}
