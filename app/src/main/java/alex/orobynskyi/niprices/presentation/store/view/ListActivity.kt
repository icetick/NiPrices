package alex.orobynskyi.niprices.presentation.store.view

import alex.orobynskyi.niprices.R
import alex.orobynskyi.niprices.databinding.ActivityListBinding
import alex.orobynskyi.niprices.presentation.base.BaseActivity
import alex.orobynskyi.niprices.presentation.store.viewModel.ListViewModel
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity: BaseActivity<ActivityListBinding, ListViewModel>() {
    override val viewModel: ListViewModel by viewModel()
    override fun getLayoutID(): Int = R.layout.activity_list

    override fun beforeContentAppear() {
    }

    override fun afterContentAppear() {
        editText.setOnQueryTextListener(viewModel.searchQueryTextListener)
        editText.setOnCloseListener {
            viewModel.removeSearchSubscriptions()
            false
        }
    }

    override fun onReleaseResources() {
    }



}