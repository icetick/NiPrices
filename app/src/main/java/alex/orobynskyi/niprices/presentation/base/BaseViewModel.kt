package alex.orobynskyi.niprices.presentation.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    abstract fun onCreated()
}