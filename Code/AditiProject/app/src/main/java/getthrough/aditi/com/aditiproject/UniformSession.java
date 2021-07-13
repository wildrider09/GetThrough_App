package getthrough.aditi.com.aditiproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UniformSession extends AppCompatActivity {
    TextView tv;
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uniform_session);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        tv = (TextView) findViewById(R.id.uniform_status);
        ll = (LinearLayout) findViewById(R.id.linea);
        if (new SessionManager(UniformSession.this).getUniform().contains(" ")) {
            Button b = new Button(UniformSession.this);
            b.setText("click to book a session");
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(UniformSession.this, BookSessionUniform.class));
                }
            });
            ll.addView(b);
        } else {
            TextView tvv = new TextView(UniformSession.this);
            tvv.setText("please wait for your session and be on time....\n if u have attended the session then your uniform\n would be delivered soon");
            ll.addView(tvv);
        }
    }
}
