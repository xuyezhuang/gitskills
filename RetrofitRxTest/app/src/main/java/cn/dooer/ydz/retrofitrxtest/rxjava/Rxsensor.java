package cn.dooer.ydz.retrofitrxtest.rxjava;

import android.hardware.SensorManager;

import rx.Observable;

public class Rxsensor {
    public static Observable<float[]> registerSensor(SensorManager sensorManager){
        return Observable.create(new SensorChangeOnSubscribe(sensorManager));
    }

}
