package main.zavrsniprojekt.offline

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OfflineViewModel: ViewModel() {

    private val _click=MutableLiveData<Boolean>()
    val click : LiveData<Boolean>
    get() = _click

    fun onClick(){
        _click.value=true
    }

    fun afterClick(){
        _click.value=false
    }
}