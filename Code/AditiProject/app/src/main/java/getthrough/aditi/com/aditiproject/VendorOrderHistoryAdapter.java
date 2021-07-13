package getthrough.aditi.com.aditiproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by keshav on 04/06/2018.
 */

public class VendorOrderHistoryAdapter extends BaseAdapter {
    List<JSONObject> currentOrderObjects;
    Context context;
    String _order_id="", _student_id="", _order_amt="", _order_status="";

    public VendorOrderHistoryAdapter(List<JSONObject> currentOrderObjects, Context context) {
        this.currentOrderObjects = currentOrderObjects;
        this.context = context;
    }

    @Override
    public int getCount() {
        return currentOrderObjects.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.vendor_order_history_adapter_layout, parent, false);
        TextView order_id = (TextView) convertView.findViewById(R.id.textView11);
        TextView student_id = (TextView) convertView.findViewById(R.id.textView14);
        TextView order_amount = (TextView) convertView.findViewById(R.id.textView15);
        TextView order_status = (TextView) convertView.findViewById(R.id.textView16);
        try {

                JSONObject job = currentOrderObjects.get(position);
               // if (job.getString("order_status").contains("pending")) {
                    _order_amt = job.getString("order_amt")+"\n";
                    _order_id =job.getString("order_id")+"\n";
                    _student_id = job.getString("student_id")+"\n";
                    _order_status = job.getString("order_status")+"\n";

                               //}

            order_id.setText("Order Id: " + _order_id);
            student_id.setText("student id: " + _student_id);
            order_amount.setText("order amt: " + _order_amt);
            order_status.setText("order status: " + _order_status);
           // _order_id=""; _student_id=""; _order_amt=""; _order_status="";

        } catch (Exception e) {

        }
        return convertView;
    }
}
