package getthrough.aditi.com.aditiproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class FoodDetails extends AppCompatActivity {
    JSONObject food_details;
    Button order;
    TextView truck_name, truck_number, truck_rating;
    LinearLayout items;
    List<String> item_name, item_price;
    JSONObject job1;
    List<String> selectedItems;
    Realm realm;
    String truck_id = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
        realm = Realm.getDefaultInstance();
        truck_name = (TextView) findViewById(R.id.textView2);
        truck_number = (TextView) findViewById(R.id.textView3);
        truck_rating = (TextView) findViewById(R.id.textView4);
        items = (LinearLayout) findViewById(R.id.item_list);
        order = (Button) findViewById(R.id.order);
        String jsonstring = getIntent().getExtras().getString("data");
        try {
            food_details = new JSONObject(jsonstring);
            truck_id = food_details.getString("truck_id");
            truck_name.setText("Truck Name: " + food_details.getString("truck_name"));
            truck_number.setText("Truck Id: " + food_details.getString("truck_id"));
            truck_rating.setText("Truck Rating: " + food_details.getString("truck_rating"));
            JSONArray jar = food_details.getJSONArray("items");

            item_name = new ArrayList<>();
            item_price = new ArrayList<>();
            for (int i = 0; i < jar.length(); i++) {
                job1 = jar.getJSONObject(i);

                item_name.add(job1.getString("item_name"));
                item_price.add(job1.getString("item_price"));
            }


            for (int i = 0; i < item_name.size(); i++) {
                selectedItems = new ArrayList<>();
                CheckBox cb = new CheckBox(FoodDetails.this);
                cb.setId(i);
                cb.setText(item_name.get(i) + " : price  " + item_price.get(i));
                cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            selectedItems.add(item_name.get(buttonView.getId()) + "," + item_price.get(buttonView.getId()));


                        } else {
                            selectedItems.remove(item_name.get(buttonView.getId()) + "," + item_price.get(buttonView.getId()));

                        }

                    }
                });
                items.addView(cb);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String result = "";
                for (int i = 0; i < selectedItems.size(); i++) {
                    result = result + selectedItems.get(i) + ",";
                }
                Intent i = new Intent(FoodDetails.this, FoodOrder.class);
                i.putExtra("dataitems", result);
                i.putExtra("truck_id", truck_id);
                startActivity(i);

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
