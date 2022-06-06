package com.eegorov.okru_oauth

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import io.flutter.plugin.common.MethodChannel
import org.json.JSONObject
import ru.ok.android.sdk.Odnoklassniki
import ru.ok.android.sdk.OkListener
import ru.ok.android.sdk.Shared
import ru.ok.android.sdk.util.OkAuthType
import ru.ok.android.sdk.util.OkScope
import java.lang.Exception


class OkruController(private val appId: String, private val appKey: String, private val context: Context, private val activity: Activity) {
    private lateinit var ok: Odnoklassniki
    private lateinit var result: MethodChannel.Result

    fun onInit() {
        ok = Odnoklassniki.createInstance(context, appId, appKey)
    }

    fun auth(result: MethodChannel.Result) {
        this.result = result
        ok.requestAuthorization(activity, null, OkAuthType.WEBVIEW_OAUTH, OkScope.VALUABLE_ACCESS)
    }


    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean? {
        if (ok.isActivityRequestOAuth(requestCode)) {
            return ok.onAuthActivityResult(requestCode, resultCode, data, OkResultListener(result))
        }
        return null
    }
}

class OkResultListener(private var result: MethodChannel.Result): OkListener {
    override fun onSuccess(json: JSONObject?) {
        result.success(json.toString())
    }

    override fun onError(error: String?) {
        result.error("", error?:"err", "")
    }

}