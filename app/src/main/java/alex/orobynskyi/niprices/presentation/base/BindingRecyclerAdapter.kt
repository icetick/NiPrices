package alex.orobynskyi.niprices.presentation.base

import alex.orobynskyi.niprices.BR
import alex.orobynskyi.niprices.utils.AnimUtils
import android.content.Context
import android.view.LayoutInflater
import android.view.View
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

    private var lastPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder<T, S> {
        val inflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = DataBindingUtil.inflate<T>(inflater, layoutId, parent, false)
        return DataBindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder<T, S>, position: Int) {
        holder.bind(BR.viewModel, items[position])
        actionListener?.let { holder.bindActionListener(BR.actionListener, it) }
        setAnimation(holder.itemView, position);
    }

    override fun onViewDetachedFromWindow(holder: DataBindingViewHolder<T, S>) {
        holder.clearAnimation()
    }

    override fun onViewAttachedToWindow(holder: DataBindingViewHolder<T, S>) {
        super.onViewAttachedToWindow(holder)
    }

    private fun setAnimation(viewToAnimate: View, position: Int) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            //val animation = loadAnimation(viewToAnimate.context, android.R.anim.slide_in_left)
            val anim = AnimUtils.provideListScaleAnimation().apply {
                duration = AnimUtils.getRandomDuration()
            }
            viewToAnimate.startAnimation(anim)
            //viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

    override fun getItemCount(): Int = items.size
}