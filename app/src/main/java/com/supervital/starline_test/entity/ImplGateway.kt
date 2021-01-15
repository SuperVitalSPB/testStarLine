package com.supervital.starline_test.entity

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

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
                if (!FConnected) {
                    /** в задании не сказано ничего, а что если нет connected для OnlineKey
                     *  я так понимаю, что надо генерить исключение.
                     *  то ответ будет не такой как в задании:
                                   out:
                                        OfflineKey("1"), Value("1")
                                        OnlineKey("2"), Value("2")
                                        OfflineKey("3"), Value("3")
                     *  надо написать письмо Саше и узнать, пока делаю так:
                     */
                    // по хорошему, тут надо цеплять callback и всё такое,
                    // но так у нас в методе всего одно присвоение - не буду заморачиваться
                    connect()
                }
                key.value
            }
        }
        return Single.just(Value(sValue))
    }
}