package com.ostrovec.tablehockey.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ostrovec.tablehockey.R
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private var TAG = "SecondLesson"
    var observable: Observable<Long> = Observable.interval(400,TimeUnit.MILLISECONDS)

    var observer: Observer<Long> = object : Observer<Long> {
        override fun onSubscribe(d: Disposable) {
            Log.e(TAG, "onSubscribe")
            compositeDisposable.add(d)
        }

        override fun onComplete() {
            Log.e(TAG, "onComplete")
        }

        override fun onNext(t: Long) {
            Log.e(TAG, "onNext")
            Log.e(TAG, t.toString())
        }

        override fun onError(e: Throwable) {
            Log.e(TAG, "onError")
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        observable.subscribe(observer)
    }
}
