package getthrough.aditi.com.aditiproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by keshav on 03/22/2018.
 */

public class MainFoodAdapter extends BaseAdapter {
    Context context;
    List<JSONObject> job1;

    public MainFoodAdapter(Context context, List<JSONObject> job1) {
        this.context = context;
        this.job1 = job1;
    }

    @Override
    public int getCount() {
        return job1.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
int pos;
    @Override
    public View getView( int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.food_adapter_layout, parent, false);
        TextView tname = (TextView) convertView.findViewById(R.id.textView2);
        TextView tnumber = (TextView) convertView.findViewById(R.id.textView3);
        TextView trating = (TextView) convertView.findViewById(R.id.textView4);
        CardView cardTruck = (CardView) convertView.findViewById(R.id.cardtruck);
        pos=position;
        try {
            tname.setText("Truck Name: "+job1.get(position).getString("truck_name"));
            tnumber.setText("Truck Id: "+job1.get(position).getString("truck_id"));
            trating.setText("Truck Rating: "+job1.get(position).getString("truck_rating"));
            cardTruck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(context,FoodDetails.class);
                    i.putExtra("data",String.valueOf(job1.get(pos)));
                    context.startActivity(i);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }
}
