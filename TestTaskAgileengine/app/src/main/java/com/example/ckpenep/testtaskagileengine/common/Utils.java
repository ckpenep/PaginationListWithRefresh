package com.example.ckpenep.testtaskagileengine.common;


import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by ckpenep on 29.03.2018.
 */

public class Utils {
    public static <T> ObservableTransformer<T, T> applySchedulers() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static String getUnixTimestamp() {
        return String.valueOf(System.currentTimeMillis() / 1000L);
    }
}
