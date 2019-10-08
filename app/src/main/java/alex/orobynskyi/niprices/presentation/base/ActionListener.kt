package alex.orobynskyi.niprices.presentation.base

interface ActionListener<T> : ItemsProvider {
    fun onClick(data: T) {}
}