package alex.orobynskyi.niprices.presentation.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class DataBindingViewHolder<T : ViewDataBinding, R>(private val binding: T) : RecyclerView.ViewHolder(binding.root) {
    fun bind(field: Int, item: R) {
        binding.setVariable(field, item)
        binding.notifyPropertyChanged(field)
    }

    fun getBinding(): T = binding

    fun bindActionListener(field: Int, actionListener: ActionListener<R>) {
        binding.setVariable(field, actionListener)
        binding.notifyPropertyChanged(field)
    }

    fun clearAnimation() {
        binding.root.clearAnimation()
    }
}