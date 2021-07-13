package getthrough.aditi.com.aditiproject;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by keshav on 03/22/2018.
 */

public class SessionManager {
    Context context;
    SharedPreferences sp;
    SharedPreferences.Editor ed;

    public SessionManager(Context context) {
        this.context = context;
        sp = context.getSharedPreferences("session", context.MODE_PRIVATE);
    }

    public void login(String studentId, String passissued, String uniformissued, String role) {
        ed = sp.edit();
        ed.putString("studentid", studentId);
        ed.putString("pass", passissued);
        ed.putString("uniform", uniformissued);
        ed.putBoolean("loggedin", true);
        ed.putString("role", role);
        ed.commit();
    }

    public void logout() {
        ed = sp.edit();
        ed.putString("studentid", "");
        ed.putString("pass", "");
        ed.putString("uniform", "");
        ed.putBoolean("loggedin", false);
        ed.putString("role", "");
        ed.commit();
    }

    public String getRole() {
        return sp.getString("role", "user");
    }

    public boolean isLoggedIn() {
        return sp.getBoolean("loggedin", false);
    }

    public String getPassAndUniform() {
        return "Pass issued: " + sp.getString("pass", "not issued") + "\nUniform issued: " + sp.getString("uniform", "not issued");
    }

    public void setUniform(String uniform_status) {
        ed = sp.edit();
        ed.putString("uniform", uniform_status);
        ed.commit();
    }

    public void setPass() {

        ed = sp.edit();
        ed.putString("pass", "issued");
        ed.commit();
    }

    public String getPass() {
        return sp.getString("pass", "not issued");
    }

    public String getUniform() {
        return sp.getString("uniform", "not issued");
    }

    public String getstudentid() {
        return sp.getString("studentid", "not loggedin");
    }
}
