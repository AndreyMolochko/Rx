package com.ostrovec.tablehockey.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ostrovec.tablehockey.R
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private var TAG = "SecondLesson"
    var observable: Observable<Long> = Observable.fromCallable (CallableGetSum(4,6) )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

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

    class CallableGetSum(var a:Long, var b: Long) : Callable<Long>{

        fun getSum(a: Long, b: Long): Long {
            return a + b
        }

        override fun call(): Long {
            return getSum(a, b)
        }

    }
}
