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

class AppUtils {
    companion object {
        fun join(separator: String, input: List<String>?): String {

            if (input == null || input.size <= 0) return ""

            val sb = StringBuilder()

            for (i in input.indices) {

                sb.append(input[i])

                // if not the last item
                if (i != input.size - 1) {
                    sb.append(separator)
                }

            }
            return sb.toString()
        }
    }
}
