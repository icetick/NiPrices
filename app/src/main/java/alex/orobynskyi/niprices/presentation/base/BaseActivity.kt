package alex.orobynskyi.niprices.presentation.base;

import alex.orobynskyi.niprices.BR
import alex.orobynskyi.niprices.R
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection
import javax.inject.Inject

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel>: AppCompatActivity(), BaseFragment.Callback {
    abstract fun beforeContentAppear()
    abstract fun afterContentAppear()
    abstract fun onReleaseResources()

    abstract fun getLayoutID(): Int
    abstract val viewModel: V

    var binding: T? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    open fun requestPermissions() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        beforeContentAppear()
        performDataBinding()
        afterContentAppear()
    }

    private fun performDataBinding() {
        binding = DataBindingUtil.setContentView(this, getLayoutID())
        binding?.setVariable(BR.viewModel, viewModel)
        viewModel.onCreated()
        binding?.executePendingBindings()
    }

    override fun onDestroy() {
        super.onDestroy()
        onReleaseResources()
    }

    override fun onStop() {
        super.onStop()
        onReleaseResources()
    }

    override fun onBackPressed() {
        showExitAlert()
    }

    inline fun <reified VM : ViewModel, T> T.viewModel(): Lazy<VM> where T : FragmentActivity {
        return lazy { ViewModelProviders.of(this, viewModelFactory).get(VM::class.java) }
    }

    private fun showExitAlert() {
        AlertDialog.Builder(this)
            .setTitle(R.string.exit_title)
            .setMessage(R.string.exit_message)
            .setPositiveButton(R.string.exit_yes) { dialogInterface, clickedButton ->
                this.finish()
            }
            .setNegativeButton(R.string.exit_no) { dialogInterface, clickedButton ->
                dialogInterface.dismiss()
            }.show()
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onFragmentAttached() {

    }

    override fun onFragmentDetached(tag: String?) {

    }
}
