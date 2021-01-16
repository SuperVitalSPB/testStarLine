package com.supervital.starline_test.entity

import android.util.Log
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import java.io.IOException
import java.util.concurrent.TimeUnit

class ImplInteractor: Interactor {


    override fun load(keys: List<Key>): Single<Map<Key, Value>> {
        val result = HashMap<Key, Value>()

        keys.forEach {
            val implGateway = ImplGateway()
            var value: Value? = null
            val itemKey = it
            implGateway.get(it)
               /*.doOnError { implGateway.connect() }*/
/*               .retryWhen { error -> error.flatMap { implGateway
                                                        .connect()
                                                        .andThen(Flowable.fromCallable { it }) }
               }*/
               /*.retry()*/
               .subscribe(object : SingleObserver<Value> {
                    override fun onSubscribe(d: Disposable) {
                        Log.d(TAG, "onSubscribe")
                    }

                    override fun onSuccess(t: Value) {
                        Log.d(TAG, "onSuccess")
                        value = t
                    }

                    override fun onError(e: Throwable) {
                        Log.d(TAG, "onError occurred: ${e}")

                        // да, это костыль, но работает, оставляю так.....
                        if (e is IOException) {
                            implGateway.connect()
                            implGateway.get(it)
                                    .subscribe(object : SingleObserver<Value> {
                                        override fun onSubscribe(d: Disposable) {}
                                        override fun onSuccess(t: Value) {
                                            value = t
                                        }
                                        override fun onError(e: Throwable) {}
                                    })
                        }
                    }
            })

            if (value != null)
                result.put(it, value!!)
        }


        return Single.just(result)
    }

    companion object {
        private val TAG = this::class.java.canonicalName
    }

}
