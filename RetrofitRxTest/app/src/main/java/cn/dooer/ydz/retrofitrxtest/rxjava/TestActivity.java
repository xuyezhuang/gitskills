package cn.dooer.ydz.retrofitrxtest.rxjava;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import cn.dooer.ydz.retrofitrxtest.R;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

public class TestActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager sensormanager;

    public static void start(Context context){
        Intent intent=new Intent(context, TestActivity.class);
        context.startActivity(intent);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        sensormanager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Rxsensor.registerSensor(sensormanager)
                .debounce(1, TimeUnit.SECONDS)
                .subscribe(new Action1<float[]>() {
                    @Override
                    public void call(float[] floats) {
                        Toast.makeText(TestActivity.this, floats[0]+floats[1]+floats[2]+"", Toast.LENGTH_SHORT).show();
                        Log.d("传感器数据",floats[0]+floats[1]+floats[2]+"");
                    }
                });
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        sensormanager.unregisterListener(this);
    }
}
