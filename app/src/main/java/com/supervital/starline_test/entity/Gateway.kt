package com.supervital.starline_test.entity

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import java.io.IOException

interface Gateway {
    // По умолчанию - false
    val connected: Observable<Boolean>

    // Переводит значение connected в true.
    fun connect(): Completable

    @Throws(IOException::class) fun get(key: Key): Single<Value>
}