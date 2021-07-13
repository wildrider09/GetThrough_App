package getthrough.aditi.com.aditiproject;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by keshav on 03/22/2018.
 */

public class UserModel extends RealmObject {

    @PrimaryKey
    String studentid;
    String studentname;
    String studentemail;
    String studentphone;
    String studentcourse;
    String stuentdept;
    String studentreceipt;
    String passissued;
    String uniformissued;
    String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassissued() {
        return passissued;
    }

    public void setPassissued(String passissued) {
        this.passissued = passissued;
    }

    public String getUniformissued() {
        return uniformissued;
    }

    public void setUniformissued(String uniformissued) {
        this.uniformissued = uniformissued;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getStudentemail() {
        return studentemail;
    }

    public void setStudentemail(String studentemail) {
        this.studentemail = studentemail;
    }

    public String getStudentphone() {
        return studentphone;
    }

    public void setStudentphone(String studentphone) {
        this.studentphone = studentphone;
    }

    public String getStudentcourse() {
        return studentcourse;
    }

    public void setStudentcourse(String studentcourse) {
        this.studentcourse = studentcourse;
    }

    public String getStuentdept() {
        return stuentdept;
    }

    public void setStuentdept(String stuentdept) {
        this.stuentdept = stuentdept;
    }

    public String getStudentreceipt() {
        return studentreceipt;
    }

    public void setStudentreceipt(String studentreceipt) {
        this.studentreceipt = studentreceipt;
    }
}
