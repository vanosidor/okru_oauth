package com.eegorov.okru_oauth

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.annotation.*

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry


/** OkruOauthPlugin */
class OkruOauthPlugin : FlutterPlugin, MethodCallHandler, ActivityAware, PluginRegistry.ActivityResultListener {
    private lateinit var okruController: OkruController

    private lateinit var channel: MethodChannel
    private lateinit var context: Context
    private lateinit var activity: Activity
	private var isActive = false
    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "okru_oauth")
        channel.setMethodCallHandler(this)
        context = flutterPluginBinding.applicationContext
    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        when (call.method) {
            "init" -> {
                okruController = OkruController(call.argument<String>("appID")
                        ?: "", call.argument<String>("appKey") ?: "", context, activity)
                okruController.onInit()
				isActive = true
                result.success(null)
            }
            "auth" -> {
                okruController.auth(result)
            }
            else -> {
                result.notImplemented()
            }
        }
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }

    override fun onDetachedFromActivity() {}

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {}

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        activity = binding.activity;
        binding.addActivityResultListener(this)
    }

    override fun onDetachedFromActivityForConfigChanges() {}

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
		if(!isActive) return true 
		isActive = false
        val res = okruController.onActivityResult(requestCode, resultCode, data)
        return res ?: true
    }
}
