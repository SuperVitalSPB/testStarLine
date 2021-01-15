package com.supervital.starline_test.entity

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import java.io.IOException

class ImplGateway: Gateway {

    var FConnected = false

    override val connected : Observable<Boolean>
        get() = Observable.just(FConnected)

    override fun connect(): Completable {
        FConnected = true
        return Completable.complete()
    }

    override fun get(key: Key): Single<Value> {
        val sValue: String = when (key) {
            is OfflineKey -> key.value
            is OnlineKey -> {
                if (!FConnected)
                    return Single.error(IOException("I can't get the key, no connection"))

                // проверку наличия соединения не делаю,
                // выше вызвал connect(), считаем, что  есть коннекция
                key.value
            }
        }
        return Single.just(Value(sValue))
    }
}