package alex.orobynskyi.niprices.utils

class NativeWrapper {
    companion object {
        init {
            System.loadLibrary("native-lib")
        }

    }

    external fun distinct(): String
}