package alex.orobynskyi.niprices.utils

import alex.orobynskyi.niprices.R
import alex.orobynskyi.niprices.presentation.base.ActionListener
import alex.orobynskyi.niprices.presentation.base.BindingRecyclerAdapter
import android.view.MotionEvent
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

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

@BindingAdapter("android:srcUrl")
fun setImageSrcUrl(view: ImageView, url: String?) {
    val nextUrl = url?.addSchema()
    Glide.with(view).load(nextUrl).error(R.drawable.ic_switch).into(view)
}

@BindingAdapter("disallowTouchEvent")
fun disallowTouchEvent(view: RecyclerView, isDisallowed: Boolean) {
    if (isDisallowed) {
        view.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                rv.parent.requestDisallowInterceptTouchEvent(isDisallowed); return false
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
        })
    }
}
