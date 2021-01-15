package com.supervital.starline_test.entity

import io.reactivex.Single

interface Interactor {

    fun load(keys: List<Key>): Single<Map<Key, Value>>

}