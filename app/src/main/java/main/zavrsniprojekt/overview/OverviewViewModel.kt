package main.zavrsniprojekt.overview

import android.graphics.Color
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import main.zavrsniprojekt.network.Data
import main.zavrsniprojekt.network.NbaApi


class OverviewViewModel : ViewModel() {
    companion object{
        var filter = ""
    }

    private val _properties = MutableLiveData<List<Data>>()
    val properties: LiveData<List<Data>>
        get() = _properties

    private val _response = MutableLiveData<List<Data>>()
    val response: LiveData<List<Data>>
        get() = _response

    private val _navigateToSelectedProperty = MutableLiveData<Data>()
    val navigateToSelectedProperty: LiveData<Data>
        get() = _navigateToSelectedProperty

    init {
        getPlayersByName()
    }

    fun getPlayersByName() {
        viewModelScope.launch {
            try {
                val obj = NbaApi.retrofitService.getPlayersByName(filter)
                if(obj != null){
                    _properties.value = obj.data
                }
            }catch (e: Exception){
                Log.d("greska: ",e.toString())
                _properties.value = ArrayList()
            }
        }
    }

    fun displayPropertyDetails(data: Data) {
        _navigateToSelectedProperty.value = data
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }

}
