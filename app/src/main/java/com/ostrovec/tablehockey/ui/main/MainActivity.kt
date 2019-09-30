package com.ostrovec.tablehockey.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ostrovec.tablehockey.R
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class MainActivity : AppCompatActivity() {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private var TAG = "SecondLesson"
    var observable: Observable<Int> = Observable.range(0,100)

    var observer: Observer<Int> = object : Observer<Int> {
        override fun onSubscribe(d: Disposable) {
            Log.e(TAG, "onSubscribe")
            compositeDisposable.add(d)
        }

        override fun onComplete() {
            Log.e(TAG, "onComplete")
        }

        override fun onNext(t: Int) {
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
