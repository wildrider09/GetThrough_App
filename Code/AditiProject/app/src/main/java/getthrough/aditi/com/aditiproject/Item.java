package getthrough.aditi.com.aditiproject;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by keshav on 03/23/2018.
 */

public class Item extends RealmObject {

    @PrimaryKey
    String item_name;
    String item_price;

    public Item() {
    }

    public Item(String item_name, String item_price) {
        this.item_name = item_name;
        this.item_price = item_price;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }
}
