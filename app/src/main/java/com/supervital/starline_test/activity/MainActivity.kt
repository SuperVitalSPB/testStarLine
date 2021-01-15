package com.supervital.starline_test.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.supervital.starline_test.R
import com.supervital.starline_test.entity.*
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import java.util.concurrent.Callable


class MainActivity : AppCompatActivity() {


    private lateinit var btnGo: Button
    private lateinit var tv_log: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnGo = findViewById(R.id.btnGo)
        btnGo.setOnClickListener(btnGo_onClick)
        tv_log = findViewById(R.id.tv_log)
    }


    val btnGo_onClick = View.OnClickListener { view ->
        tv_log.text = ""
        val inList = listOf(OfflineKey("1"), OnlineKey("2"), OfflineKey("3"))
        val impInteractor = ImplInteractor()
// Single<Map<Key, Value>>
        impInteractor.load(inList).subscribe(object : SingleObserver<Map<Key, Value>> {
            override fun onSubscribe(d: Disposable) {
                addToLog("in:\n\tlistOf(${inList})")
            }

            override fun onSuccess(t: Map<Key, Value>) {
                addToLog( "\n out:\n\t ${t}")
            }

            override fun onError(e: Throwable) {
                addToLog("onError occurred: ${e}")
            }

        })
    }

    @SuppressLint("SetTextI18n")
    fun addToLog(sStr: String) {
        tv_log.setText("${tv_log.text}\n${sStr}")
    }

    companion object {
        private val TAG = this::class.java.canonicalName
    }
}

