package getthrough.aditi.com.aditiproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by keshav on 03/23/2018.
 */

public class OrderHistoryAdapter extends BaseAdapter {
    Context context;
    List<String> order_id, order_amt, order_status, student_id;

    public OrderHistoryAdapter(Context context,
                               List<String> order_id, List<String> order_amt,
                               List<String> order_status, List<String> student_id) {
        this.context = context;
        this.order_id = order_id;
        this.order_amt = order_amt;
        this.order_status = order_status;
        this.student_id = student_id;
    }

    @Override
    public int getCount() {
        return order_id.size();
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
        convertView = LayoutInflater.from(context).inflate(R.layout.historylay, parent, false);
        TextView tv1 = (TextView) convertView.findViewById(R.id.textView7);
        TextView tv2 = (TextView) convertView.findViewById(R.id.textView8);
        TextView tv3 = (TextView) convertView.findViewById(R.id.textView9);
        TextView tv4 = (TextView) convertView.findViewById(R.id.textView10);

        if (student_id.get(position).contains(new SessionManager(context).getstudentid())) {
            tv1.setText("order id: " + order_id.get(position));
            tv2.setText("order amount: " + order_amt.get(position));
            tv3.setText("order status: " + order_status.get(position));
            tv4.setText("student id: " + student_id.get(position));
        }
        return convertView;
    }
}
