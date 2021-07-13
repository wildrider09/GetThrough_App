package getthrough.aditi.com.aditiproject;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class GenerateBusPass extends AppCompatActivity {
    Button uploadPic, generatePass;
    EditText student_name, fathers_name, student_id;
    String st_name, st_fa, st_stid, passno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_generate_bus_pass);
        uploadPic = (Button) findViewById(R.id.button2);
        generatePass = (Button) findViewById(R.id.button3);
        student_id = (EditText) findViewById(R.id.editText6);
        student_name = (EditText) findViewById(R.id.editText4);
        fathers_name = (EditText) findViewById(R.id.editText5);
        generatePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


              /*  $passno=$_REQUEST['passno'];
                $student_id=$_REQUEST['student_id'];
                $student_name=$_REQUEST['stu_name'];
                $fathers_name=$_REQUEST['fathers_name'];*/

                st_name = student_name.getText().toString();
                st_fa = fathers_name.getText().toString();
                st_stid = student_id.getText().toString();
                passno = st_name + "3412";
                RequestQueue requestQueue = Volley.newRequestQueue(MyApplication.getInstance(), new HurlStack(null, ClientSSLSocketFactory.getSocketFactory()));
                StringRequest sr = new StringRequest(Request.Method.POST, "http://aditi.atwebpages.com/createpass.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response != null) {
                                    new SessionManager(GenerateBusPass.this).setPass();
                                    AlertDialog.Builder adb = new AlertDialog.Builder(GenerateBusPass.this);
                                    adb.setMessage("Bus pass generated successfully. Please go to pass view section to see it.\n Thanks and regards\nManagement");
                                    adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                                    adb.show();
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
                        params.put("passno", passno);
                        params.put("student_id", st_stid);
                        params.put("stu_name", st_name);
                        params.put("fathers_name", st_fa);
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
        uploadPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
