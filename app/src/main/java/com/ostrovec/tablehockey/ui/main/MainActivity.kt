package com.ostrovec.tablehockey.ui.main

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ostrovec.tablehockey.R
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Function
import io.reactivex.observables.ConnectableObservable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private var TAG = "SecondLesson"
    var observable: ConnectableObservable<String> = Observable.interval(1, TimeUnit.SECONDS)
        .take(8)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .map {
            longToString(it)
        }.takeUntil { it == "6" }.replay()

    var firstObserver: Observer<String> = object : Observer<String> {
        override fun onSubscribe(d: Disposable) {
            Log.e(TAG, "firstObserver onSubscribe")
            compositeDisposable.add(d)
        }

        override fun onComplete() {
            Log.e(TAG, "firstObserver onComplete")
        }

        override fun onNext(t: String) {
            Log.e(TAG, "firstObserver onNext")
            Log.e(TAG, t)
        }

        override fun onError(e: Throwable) {
            Log.e(TAG, "firstObserver onError")
        }

    }

    var secondObserver: Observer<String> = object : Observer<String> {
        override fun onComplete() {
            Log.e(TAG, "secondObserver onComplete")
        }

        override fun onSubscribe(d: Disposable) {
            Log.e(TAG, "secondObserver onSubscribe")
            compositeDisposable.add(d)
        }

        override fun onNext(t: String) {
            Log.e(TAG, "secondObserver onNext")
            Log.e(TAG, t)
        }

        override fun onError(e: Throwable) {
            Log.e(TAG, "secondObserver onError")
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        Log.e(TAG, "observable connect")
        Handler().postDelayed({
            Log.e(TAG, "subscribing first")
            observable.subscribe(firstObserver)
        }, 2200)
        compositeDisposable.add(observable.connect())

        Handler().postDelayed({
            Log.e(TAG, "subscribing second")
            observable.subscribe(secondObserver)
        }, 4200)

    }

    fun longToString(number: Long): String {
        return number.toString()
    }
}
