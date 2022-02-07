package kr.co.volumecontrolview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RatingBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);

        final RatingBar ratingBar = findViewById(R.id.ratingBar);
        VolumeControlView view = findViewById(R.id.volume);

        view.setKnobListener(new VolumeControlView.knobListener() {
            @Override
            public void onChanged(double angle) {
                float rating = ratingBar.getRating();
                if(angle > 0 && rating < 7.0) {
                    // 오른쪽 회전
                    ratingBar.setRating(rating + 1.0f);
                } else if(rating > 0.0) {
                    ratingBar.setRating(rating - 1.0f);
                }
            }
        });
    }
}