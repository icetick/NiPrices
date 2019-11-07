package alex.orobynskyi.niprices.presentation.store.view

import alex.orobynskyi.niprices.R
import alex.orobynskyi.niprices.databinding.ActivityMainBinding
import alex.orobynskyi.niprices.presentation.base.BaseActivity
import alex.orobynskyi.niprices.presentation.store.viewModel.MainViewModel
import alex.orobynskyi.niprices.utils.NativeWrapper
import android.content.Intent
import android.view.animation.AnimationUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override fun getLayoutID(): Int = R.layout.activity_main
    override val viewModel: MainViewModel by viewModel()

    override fun beforeContentAppear() {
        Toast.makeText(this, NativeWrapper().distinct(), Toast.LENGTH_LONG).show()
        //viewModel.loadAllEuPosts()
    }

    override fun afterContentAppear() {
        val animation = AnimationUtils.loadAnimation(this,
            R.anim.fade_in_out_anim
        )
        viewModel.proceed.observeForever {
            if(it) {
                val mainIntent = Intent(this, ListActivity::class.java)
                startActivity(mainIntent)
                finish()
            }
        }
        logoIV.startAnimation(animation)
    }

    override fun onReleaseResources() {
    }
}
