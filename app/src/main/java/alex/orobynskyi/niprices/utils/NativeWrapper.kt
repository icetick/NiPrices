package alex.orobynskyi.niprices.utils

object NativeWrapper {
    init {
        System.loadLibrary("native-lib")
    }

    external fun multiply(value: Double, rate: Double): Double
}