package main.zavrsniprojekt.uvod

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import main.zavrsniprojekt.database.Player
import main.zavrsniprojekt.database.PlayerDatabaseDao

class UvodViewModel: ViewModel() {

    val uvod = Uvod()
    private var _listAllPlayer = MutableLiveData<Boolean>()
    val listAllPlayer: LiveData<Boolean>
        get() = _listAllPlayer

    private var _listPlayerDatabase = MutableLiveData<Boolean>()
    val listPlayerDatabase: LiveData<Boolean>
    get() = _listPlayerDatabase


    fun nextPage(){
        _listAllPlayer.value = true
    }

    fun finish(){
        _listAllPlayer.value = false
    }

    fun playerDatabase(){
        _listPlayerDatabase.value = true
    }

    fun playerFinish(){
        _listPlayerDatabase.value = false
    }
}