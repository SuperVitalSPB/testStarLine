# testStarLine

тестовое задание при приеме на работу, на должность android developer
//Дано:
sealed class Key()
data class OfflineKey(val value: String): Key()
data class OnlineKey(val value: String): Key()
data class Value(val value: String)
interface Gateway {
 // По умолчанию - false
 val connected: Observable<Boolean>
 // Переводит значение connected в true.
 fun connect(): Completable
 @Throws(IOException::class)
 fun get(key: Key): Single<Value>
}
interface Interactor {
 fun load(keys: List<Key>) : Single<Map<Key, Value>>
}
/*
1. Имплементировать Gateway и Interactor.
 Gateway умеет возвращать значения содержащиеся во входном объекте Key.
 Для объектов типа OnlineKey возвращает значение только если есть соединение.
 Признак наличия соединения содержится в поле connected.
 Interactor умеет загружать все значения через метод Gateway.get
 В случае ошибки получения данных устанавливает соединение через метод
Gateway.connect() и продолжает загрузку.
2. Написать тест для interactor.load
 Пример работы теста:
 in:
 listOf(OfflineKey("1"), OnlineKey("2"), OfflineKey("3"))
 out:
 OfflineKey("1"), Value("1")
 OnlineKey("2"), Value("2")
 OfflineKey("3"), Value("3")
 Вопросы по заданию можно задавать на xxxxxxxxx.xx@starline.ru
*/
