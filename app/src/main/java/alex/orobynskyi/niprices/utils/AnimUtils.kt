package alex.orobynskyi.niprices.utils

import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import kotlin.random.Random

class AnimUtils {
    companion object {
        fun getRandomDuration(): Long {
            val minimumDuration: Long = 200
            val offset: Long = 300
            return Random(offset).nextLong() + minimumDuration
        }

        fun provideListScaleAnimation(): ScaleAnimation {
            return ScaleAnimation(
                0.0f,
                1.0f,
                0.0f,
                1.0f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f
            )
        }
    }
}