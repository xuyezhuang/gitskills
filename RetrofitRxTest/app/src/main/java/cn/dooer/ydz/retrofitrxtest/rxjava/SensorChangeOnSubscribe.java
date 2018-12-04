package cn.dooer.ydz.retrofitrxtest.rxjava;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import rx.Observable;
import rx.Subscriber;


public class SensorChangeOnSubscribe implements rx.Observable.OnSubscribe<float[]> {

    final SensorManager sensorManager;

    public SensorChangeOnSubscribe(SensorManager sensorManager){
        this.sensorManager=sensorManager;
    }

    @Override
    public void call(final Subscriber<? super float[]> subscriber) {
        final SensorEventListener listener=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float[] values = sensorEvent.values;
                subscriber.onNext(values);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
       sensorManager.registerListener(listener,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
//        subscriber.add(new MainThreadSubscription() {
//
//            protected void onUnsubscribe() {
//                sensorManager.unregisterListener(listener);
//            }  });
//
//        subscriber.onNext(new float[]{0,0,0});//传感器没有注册的时候返回000,有变化的时候进入chaanged方法注册并返回数据
   }
}
