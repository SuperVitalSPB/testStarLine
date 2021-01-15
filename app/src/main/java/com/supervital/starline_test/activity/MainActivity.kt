package com.supervital.starline_test.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.supervital.starline_test.R
import com.supervital.starline_test.entity.*
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import java.util.concurrent.Callable


class MainActivity : AppCompatActivity() {


    private lateinit var btnGo: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnGo = findViewById(R.id.btnGo)
        btnGo.setOnClickListener(btnGo_onClick)
    }


    val btnGo_onClick = View.OnClickListener { view ->
        val inList = listOf(OfflineKey("1"), OnlineKey("2"), OfflineKey("3"))
        val impInteractor = ImplInteractor()
// Single<Map<Key, Value>>
        impInteractor.load(inList).subscribe(object : SingleObserver<Map<Key, Value>> {
            override fun onSubscribe(d: Disposable) {
                Log.d(TAG,"onSubscribe occurred")
                val sStr = "in:\n\tlistOf(${inList})"
                Log.d(TAG, sStr)
            }

            override fun onSuccess(t: Map<Key, Value>) {
                Log.d(TAG,"onSuccess")
                val sStr = "out:\n\t ${t}"
                Log.d(TAG, sStr)
            }

            override fun onError(e: Throwable) {
                Log.d(TAG,"onError occurred: ${e}")
            }

        })


    }

    companion object {
        private val TAG = this::class.java.canonicalName
    }
}

