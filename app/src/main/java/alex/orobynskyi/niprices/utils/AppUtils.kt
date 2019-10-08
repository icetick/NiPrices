package alex.orobynskyi.niprices.utils

import alex.orobynskyi.niprices.BuildConfig
import android.util.Log


fun Any.logE(message: String?) {
    message?.let {msg ->
        if(BuildConfig.DEBUG) {
            Log.e("NIPRICES", msg)
        }
    }
}