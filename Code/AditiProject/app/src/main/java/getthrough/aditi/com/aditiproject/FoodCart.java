package getthrough.aditi.com.aditiproject;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by keshav on 03/23/2018.
 */

public class FoodCart extends RealmObject {
    Item items;
    @PrimaryKey
    String truck_id;

    public Item getItems() {
        return items;
    }

    public void setItems(Item items) {
        this.items = items;
    }

    public String getTruck_id() {
        return truck_id;
    }

    public void setTruck_id(String truck_id) {
        this.truck_id = truck_id;
    }
}
