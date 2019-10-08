package alex.orobynskyi.niprices.presentation.base

import alex.orobynskyi.niprices.BR
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BindingRecyclerAdapter<T : ViewDataBinding, S>(
    val layoutId: Int,
    val actionListener: ActionListener<S>? = null,
    items: List<S> = arrayListOf()
) : RecyclerView.Adapter<DataBindingViewHolder<T, S>>() {

    var items = items
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder<T, S> {
        val inflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = DataBindingUtil.inflate<T>(inflater, layoutId, parent, false)
        return DataBindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder<T, S>, position: Int) {
        holder.bind(BR.viewModel, items[position])
        actionListener?.let { holder.bindActionListener(BR.actionListener, it) }
    }

    override fun onViewDetachedFromWindow(holder: DataBindingViewHolder<T, S>) {
        /*      holder.clearAnimation()
              animatedViews.remove(holder.itemView.id)*/
    }

    override fun onViewAttachedToWindow(holder: DataBindingViewHolder<T, S>) {
        super.onViewAttachedToWindow(holder)
    }

    override fun getItemCount(): Int = items.size
}