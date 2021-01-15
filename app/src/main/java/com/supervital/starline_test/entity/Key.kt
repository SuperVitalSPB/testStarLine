package com.supervital.starline_test.entity

sealed class Key() {


}


data class OfflineKey(val value: String): Key() {

}


data class OnlineKey(val value: String): Key() {

}


