package getthrough.aditi.com.aditiproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.gc.materialdesign.views.ButtonRectangle;

import java.util.HashMap;

public class Home extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    ButtonRectangle uniform, bus, food;
    //ViewPager vp;
    TextView passanduniform;
    SliderLayout sliderLayout;
    HashMap<String,String> Hash_file_maps ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        passanduniform = (TextView) findViewById(R.id.passanduniform);
        SessionManager sm = new SessionManager(Home.this);
        passanduniform.setText(sm.getPassAndUniform());
        //vp = (ViewPager) findViewById(R.id.viewPager);
        uniform = (ButtonRectangle) findViewById(R.id.uniform_home);
        bus = (ButtonRectangle) findViewById(R.id.bus_home);
        food = (ButtonRectangle) findViewById(R.id.food_home);
        uniform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, UniformSession.class));
            }
        });
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, Food.class));
            }
        });
        bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, ViewBusPass.class));
            }
        });
        Hash_file_maps = new HashMap<String, String>();

        sliderLayout = (SliderLayout)findViewById(R.id.slider);

        Hash_file_maps.put("Welcome", "http://www.bigfoto.com/lines-image.jpg");
        Hash_file_maps.put("To", "http://www.picture-newsletter.com/pictures-homepage/pyramids.jpg");
        Hash_file_maps.put("Our", "http://www.picture-newsletter.com/pictures-homepage/sun-sunset-2000mm.jpg");
        Hash_file_maps.put("Project", "http://www.picture-newsletter.com/pictures-homepage/sunset-tree.jpg");
        for(String name : Hash_file_maps.keySet()){

            TextSliderView textSliderView = new TextSliderView(Home.this);
            textSliderView
                    .description(name)
                    .image(Hash_file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);
            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(3000);
        sliderLayout.addOnPageChangeListener(this);
    }
    @Override
    protected void onStop() {

        sliderLayout.stopAutoCycle();

        super.onStop();

}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            SessionManager sm = new SessionManager(Home.this);
            sm.logout();

            startActivity(new Intent(Home.this, Login.class));
            finish();
        }
        return true;
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
