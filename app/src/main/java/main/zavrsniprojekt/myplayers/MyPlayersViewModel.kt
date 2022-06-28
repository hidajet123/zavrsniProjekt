package main.zavrsniprojekt.myplayers

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import main.zavrsniprojekt.database.Player
import main.zavrsniprojekt.database.PlayerDatabaseDao

class MyPlayersViewModel(
    private val databaseDao: PlayerDatabaseDao,
    private var application: Application
) : ViewModel(), MyPlayersAdapter.OnItemClickListener {
    val listener = this
    private val _lista = MutableLiveData<MutableList<Player?>>()
    val lista: MutableLiveData<MutableList<Player?>>
        get() = _lista

    private val _delete= MutableLiveData<Int>()
    val delete: MutableLiveData<Int>
    get() = _delete


    init {
        setList()
    }

    fun onClear() {
        viewModelScope.launch {
            delete()
        }
    }

    private suspend fun delete() {
        databaseDao.clear()
        setList()
    }

    private fun setList() {
        viewModelScope.launch {
            _lista.value = databaseDao.get()
        }
    }

    override fun onItemClick(position: Int) {
            _delete.value= position

    }

}