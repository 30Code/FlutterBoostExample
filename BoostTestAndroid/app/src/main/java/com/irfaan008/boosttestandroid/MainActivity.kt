package com.irfaan008.boosttestandroid

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.idlefish.flutterboost.EventListener
import com.idlefish.flutterboost.FlutterBoost
import com.idlefish.flutterboost.FlutterBoostRouteOptions
import com.idlefish.flutterboost.ListenerRemover
import com.idlefish.flutterboost.containers.FlutterBoostActivity
import com.irfaan008.boosttestandroid.channel.event.EventChannelHelper
import com.irfaan008.boosttestandroid.constant.Constant
import com.irfaan008.boosttestandroid.databinding.ActivityMainBinding
import com.irfaan008.boosttestandroid.utils.FPreferencesUtil
import com.irfaan008.boosttestandroid.utils.LogUtil
import io.flutter.embedding.android.FlutterActivityLaunchConfigs


class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE: Int = 520

    private lateinit var binding: ActivityMainBinding

    private var remover : ListenerRemover? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val isLogin : Boolean = FPreferencesUtil.getBoolean(Constant.LOGIN_KEY, false)
        if (isLogin) {
            binding.btLogin.visibility = View.GONE
        }

        observerNetwork()

        val listener = EventListener { key, args ->
            LogUtil.i("MEventListener key = $key ,args = $args")
            FPreferencesUtil.putBoolean(Constant.LOGIN_KEY, true)
            binding.btLogin.visibility = View.GONE
        }
        remover = FlutterBoost.instance().addEventListener("login_event", listener)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        LogUtil.i("MainActivity onActivityResult resCode = $resultCode requestCode = $requestCode")
        if (requestCode == REQUEST_CODE) {
            if (data != null) {

            }
        }
    }

    fun goLoginFlutter(view: View) {
        // 通过 flutterboost 的api 跳转flutter 页面
//        val params : Map<String, String> = HashMap()
        val intent: Intent = FlutterBoostActivity.CachedEngineIntentBuilder(
            FlutterBoostActivity::class.java)
            .backgroundMode(FlutterActivityLaunchConfigs.BackgroundMode.opaque)
            .destroyEngineWithActivity(false)
            .url("loginPage")
//            .urlParams(params)
            .build(this@MainActivity)
        startActivityForResult(intent, REQUEST_CODE)
    }

    fun go2Flutter(view: View) {
        // 通过 flutterboost 的api 跳转flutter 页面
        val options = FlutterBoostRouteOptions.Builder()
            .pageName("/")
            .arguments(emptyMap())
            .requestCode(1111)
            .build()
        FlutterBoost.instance().open(options)
    }

    /**
     * 模拟网络环境，每个1s，发送一次
     */
    private fun observerNetwork() {
        val handler = Handler(Looper.getMainLooper())
        var netValue = 1
        handler.postDelayed(object : Runnable {
            override fun run() {
                EventChannelHelper.eventSink?.success(netValue++)
                handler.postDelayed(this, 2000)
            }
        }, 500)
    }

    override fun onDestroy() {
        super.onDestroy()
        remover?.remove();
    }
}