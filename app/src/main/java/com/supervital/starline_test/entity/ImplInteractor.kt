package com.supervital.starline_test.entity

import android.util.Log
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

class ImplInteractor: Interactor {


    override fun load(keys: List<Key>): Single<Map<Key, Value>> {
        val result = HashMap<Key, Value>()

        keys.forEach {
            val implGateway = ImplGateway()
            var value: Value? = null

            implGateway.get(it).subscribe(object : SingleObserver<Value> {
                override fun onSubscribe(d: Disposable) {
                    Log.d(TAG, "onSubscribe")
                }

                override fun onSuccess(t: Value) {
                    Log.d(TAG, "onSuccess")
                    value = t
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "onError occurred: ${e}")
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