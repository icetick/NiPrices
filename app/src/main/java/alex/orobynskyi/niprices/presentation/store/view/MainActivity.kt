package alex.orobynskyi.niprices.presentation.store.view

import alex.orobynskyi.niprices.R
import alex.orobynskyi.niprices.databinding.ActivityMainBinding
import alex.orobynskyi.niprices.presentation.base.BaseActivity
import alex.orobynskyi.niprices.presentation.store.viewModel.MainViewModel
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override fun getLayoutID(): Int = R.layout.activity_main
    override val viewModel: MainViewModel by viewModel()

    override fun beforeContentAppear() {
        viewModel.loadAllEuPosts()
    }

    override fun afterContentAppear() {
        val animation = AnimationUtils.loadAnimation(this,
            R.anim.fade_in_out_anim
        )
        logoIV.startAnimation(animation)
    }

    override fun onReleaseResources() {
    }
}
