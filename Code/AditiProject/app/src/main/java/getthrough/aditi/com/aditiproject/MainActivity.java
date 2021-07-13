package getthrough.aditi.com.aditiproject;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;

import app.Config;
import utils.NotificationUtils;

public class MainActivity extends Activity {
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(3000);
                    mRegistrationBroadcastReceiver = new BroadcastReceiver() {
                        @Override
                        public void onReceive(Context context, Intent intent) {

                            // checking for type intent filter
                            if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                                // gcm successfully registered
                                // now subscribe to `global` topic to receive app wide notifications
                                FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

                                displayFirebaseRegId();

                            } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                                // new push notification is received

                                String message = intent.getStringExtra("message");

                                Toast.makeText(getApplicationContext(), "Push notification: " + message, Toast.LENGTH_LONG).show();


                            }
                        }
                    };
                    SessionManager sm = new SessionManager(MainActivity.this);
                    if (sm.isLoggedIn()&&sm.getRole().contains("student")) {
                        startActivity(new Intent(MainActivity.this, Home.class));
                    }else if (sm.isLoggedIn()&&sm.getRole().contains("food vendor")) {
                        startActivity(new Intent(MainActivity.this, FoodVendor.class));
                    }else if (sm.isLoggedIn()&&sm.getRole().contains("uniform vendor")){
                        startActivity(new Intent(MainActivity.this, UniformVendor.class));
                    }
                    else  {
                        startActivity(new Intent(MainActivity.this, Login.class));
                    }
                } catch (Exception e) {

                }
            }
        }.start();


    }

    private void displayFirebaseRegId() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        String regId = pref.getString("regId", null);


        if (!TextUtils.isEmpty(regId))
            Toast.makeText(this, "" + regId, Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "some problems", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));

        // clear the notification area when the app is opened
        NotificationUtils.clearNotifications(getApplicationContext());
    }
}
