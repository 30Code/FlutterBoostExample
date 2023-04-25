package com.irfaan008.boosttestandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.idlefish.flutterboost.FlutterBoost
import com.idlefish.flutterboost.FlutterBoostRouteOptions

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun openAccount(view: View) {
        // 通过 flutterboost 的api 跳转flutter 页面
        val options = FlutterBoostRouteOptions.Builder()
            .pageName("1002")
            .arguments(emptyMap())
            .requestCode(1111)
            .build()
        FlutterBoost.instance().open(options)
    }
}