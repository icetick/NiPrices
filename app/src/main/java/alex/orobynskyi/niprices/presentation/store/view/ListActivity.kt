package alex.orobynskyi.niprices.presentation.store.view

import alex.orobynskyi.niprices.R
import alex.orobynskyi.niprices.databinding.ActivityListBinding
import alex.orobynskyi.niprices.presentation.base.BaseActivity
import alex.orobynskyi.niprices.presentation.store.viewModel.ListViewModel

class ListActivity: BaseActivity<ActivityListBinding, ListViewModel>() {
    override val viewModel: ListViewModel by viewModel()
    override fun getLayoutID(): Int = R.layout.activity_list

    override fun beforeContentAppear() {
    }

    override fun afterContentAppear() {

    }

    override fun onReleaseResources() {
    }



}