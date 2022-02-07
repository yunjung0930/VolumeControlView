package kr.co.volumecontrolview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class VolumeControlView extends AppCompatImageView implements View.OnTouchListener {

    private double angle = 0.0;
    private knobListener listener;
    float x, y;
    float mx, my;


    public VolumeControlView(@NonNull Context context) {
        super(context);
        this.setImageResource(R.drawable.knob);
        this.setOnTouchListener(this);
    }

    public VolumeControlView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setImageResource(R.drawable.knob);
        this.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        x = motionEvent.getX(0);
        y = motionEvent.getY(0);
        angle = getAngle(x, y);
        invalidate();
        listener.onChanged(angle);
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        canvas.rotate((float) angle, getWidth()/2, getHeight()/2);

        super.onDraw(canvas);
    }

    // 두 점의 각도 구하기
    private double getAngle(float x, float y) {
        mx = x - (getWidth()/2.0f);
        my = (getHeight()/2.0f) - y;
        double degree = Math.atan2(mx, my) * 180.0 / 3.141592;      // 각도로 변환
        return degree;
    }

    public interface knobListener {
        public void onChanged(double angle);
    }

    public void setKnobListener(knobListener listener) {
        this.listener = listener;
    }
}