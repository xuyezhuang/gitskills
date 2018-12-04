package cn.dooer.ydz.retrofitrxtest.dagger2;

import android.content.Context;

import javax.inject.Inject;

import dagger.Provides;

public class Person {
    private String name="我是你爷爷";
    public Context context;


    public Person(Context context ){
            this.context=context;
        }

    public Person(String name){
        this.name=name;
    }

    public String getName(){
        return this.name;
    }
}
