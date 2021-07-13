package getthrough.aditi.com.aditiproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.realm.Realm;

public class Login extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText stu_id, stu_pass;
    Button login;
    String studentid = "", studentpassword = "", _role = " ";
    Realm realm;
    Spinner role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        stu_id = (EditText) findViewById(R.id.studentid);
        stu_pass = (EditText) findViewById(R.id.stu_pass);
        role = (Spinner) findViewById(R.id.role);
        List<String> roleList = new ArrayList<>();
        roleList.add("student");
        roleList.add("food vendor");
        roleList.add("uniform vendor");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, roleList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        role.setAdapter(adapter);
        role.setOnItemSelectedListener(this);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentid = stu_id.getText().toString();
                studentpassword = stu_pass.getText().toString();
                RequestQueue requestQueue = Volley.newRequestQueue(MyApplication.getInstance(), new HurlStack(null, ClientSSLSocketFactory.getSocketFactory()));
                StringRequest sr = new StringRequest(Request.Method.POST, "http://aditi.atwebpages.com/login.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    realm = Realm.getDefaultInstance();
                                    realm.beginTransaction();
                                    UserModel um = new UserModel();
                                    SessionManager sm = new SessionManager(Login.this);
                                    JSONObject job = new JSONObject(response);
                                    if (job != null) {
                                        JSONArray jar = job.getJSONArray("res");
                                        for (int i = 0; i < jar.length(); i++) {
                                            JSONObject job1 = jar.getJSONObject(i);
                                            um.setStudentid(job1.getString("studentid"));
                                            um.setStudentname(job1.getString("student_name"));
                                            um.setStudentemail(job1.getString("student_email"));
                                            um.setStudentcourse(job1.getString("student_course"));
                                            um.setStudentphone(job1.getString("student_phone"));
                                            um.setStuentdept(job1.getString("student_dept"));
                                            um.setStudentreceipt(job1.getString("student_receipt"));
                                            um.setPassissued(job1.getString("pass_issued"));
                                            um.setUniformissued(job1.getString("uniform_issued"));
                                            um.setRole(job1.getString("role"));

                                            sm.login(studentid, job1.getString("pass_issued"),
                                                    job1.getString("uniform_issued"), job1.getString("role"));
                                            realm.copyToRealmOrUpdate(um);
                                            realm.commitTransaction();
                                        }
                                        if (sm.getRole().contains("student")) {
                                            startActivity(new Intent(Login.this, Home.class));

                                        } else if (sm.getRole().contains("food vendor")) {
                                            startActivity(new Intent(Login.this, FoodVendor.class));
                                        } else {
                                            startActivity(new Intent(Login.this, UniformVendor.class));
                                        }
                                    }
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
                ) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("studentid", studentid);
                        params.put("role", _role);
                        params.put("password", studentpassword);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        _role = (String) parent.getItemAtPosition(position);
        Toast.makeText(this, _role, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
