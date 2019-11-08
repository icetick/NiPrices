#include <jni.h>
#include <string>
#include <cmath>

extern "C" {
    JNIEXPORT jdouble JNICALL Java_alex_orobynskyi_niprices_utils_NativeWrapper_multiply(JNIEnv *env,jobject thiz, jdouble value, jdouble rate) {
        value *= rate;
        //Rounding numbers
        jdouble result = std::ceil(value * 100.0) / 100.0;
        return result;
    }
}