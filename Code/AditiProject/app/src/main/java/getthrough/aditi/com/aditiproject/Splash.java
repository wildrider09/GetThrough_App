package getthrough.aditi.com.aditiproject;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.github.florent37.viewanimator.ViewAnimator;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.skyfishjy.library.RippleBackground;

public class Splash extends AppCompatActivity {
ImageView imageView;
    RippleBackground rippleBackground;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        rippleBackground = (RippleBackground) findViewById(R.id.content);
        imageView = (ImageView) findViewById(R.id.imaDit);
        rippleBackground.startRippleAnimation();
        ImageView imageViews = (ImageView) findViewById(R.id.centerImage);
        ViewAnimator
                .animate(imageView)
                .translationY(-1000, 0)
                .alpha(0, 1)
                .dp().translationX(-20, 0)
                .decelerate()
                .duration(5000)
                .thenAnimate(imageView)
                .scale(1f, 0.5f, 1f)
                .accelerate()
                .duration(5000)
                .start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    Intent i=new Intent(Splash.this,MainActivity.class);
                    startActivity(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
