package main.zavrsniprojekt

import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.InverseMethod
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import kotlinx.coroutines.launch
import main.zavrsniprojekt.database.Player
import main.zavrsniprojekt.database.PlayerDatabase
import main.zavrsniprojekt.network.Data
import main.zavrsniprojekt.network.NbaApi

class FilterFagmentViewModel : ViewModel(), Observable {


    private val callbacks: PropertyChangeRegistry by lazy { PropertyChangeRegistry() }

    val _readyToFilter = MutableLiveData<String>()
    val readyToFilter: LiveData<String>
        get() = _readyToFilter

    @Bindable
    var textInput = MutableLiveData<String>()

    var textType = ""

    fun filterujListuPodataka() {
        _readyToFilter.value = textInput.value.toString()
        Log.d("Value za input filter: ", _readyToFilter.value.toString())
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.remove(callback)
    }
}