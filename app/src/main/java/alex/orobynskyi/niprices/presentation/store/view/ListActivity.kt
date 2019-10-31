package alex.orobynskyi.niprices.presentation.store.view

import alex.orobynskyi.niprices.R
import alex.orobynskyi.niprices.databinding.ActivityListBinding
import alex.orobynskyi.niprices.presentation.base.BaseActivity
import alex.orobynskyi.niprices.presentation.store.viewModel.ListViewModel
import android.content.Intent
import android.net.Uri
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_list.*


class ListActivity: BaseActivity<ActivityListBinding, ListViewModel>() {
    override val viewModel: ListViewModel by viewModel()
    override fun getLayoutID(): Int = R.layout.activity_list

    override fun beforeContentAppear() {

    }

    override fun afterContentAppear() {
        search_view.setOnQueryTextListener(viewModel.searchQueryTextListener)
        search_view.setOnCloseListener {
            viewModel.removeSearchSubscriptions()
            false
        }
        viewModel.chosenGameUrl.observe(this, Observer {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
            startActivity(browserIntent)
        })
        viewModel.currencies.observe(this, Observer {
            if(it.isNotEmpty()) {
                val currencyKeys: List<String> = it.values.map { currency -> currency.currencyId + " - " + currency.currencyName }
                val adapter = ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_spinner_dropdown_item,
                    currencyKeys.toTypedArray()
                )
                currency_spinner.adapter = adapter
                currency_spinner.prompt = "Currency"
                currency_spinner.setSelection(1)
            }
        })
    }

    override fun onReleaseResources() {
    }



}