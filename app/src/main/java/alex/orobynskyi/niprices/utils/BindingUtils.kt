package alex.orobynskyi.niprices.utils

import alex.orobynskyi.niprices.presentation.base.ActionListener
import alex.orobynskyi.niprices.presentation.base.BindingRecyclerAdapter
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("listItems", "callbackHandler", "listLayout")
fun <T> setListItems(
    recyclerView: RecyclerView,
    items: MutableLiveData<List<T>>,
    callbackHandler: ActionListener<T>,
    layoutItem: Int
) {
    items.observeForever {
        it?.let { list ->
            recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
            recyclerView.adapter = BindingRecyclerAdapter<ViewDataBinding, T>(layoutItem, callbackHandler, list)
        }
    }
}