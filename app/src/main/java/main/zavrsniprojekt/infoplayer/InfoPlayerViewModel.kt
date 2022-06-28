package main.zavrsniprojekt.infoplayer

import android.graphics.Color
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import main.zavrsniprojekt.R
import main.zavrsniprojekt.database.Player
import main.zavrsniprojekt.database.PlayerDatabaseDao

class InfoPlayerViewModel(
    private val database: PlayerDatabaseDao,
    val igrac: String,
    surname: String,
    val team: String,
    val position: String
) : ViewModel() {
    var player = igrac + " " + surname

    var color = 0

    private val _sharePlayer= MutableLiveData<Boolean>()
    val sharePlayer: LiveData<Boolean>
        get() = _sharePlayer

    init {
        if(position == "C")
            color = Color.rgb(163, 238, 240)
        else if(position == "F")
            color = Color.rgb(225, 230, 225)
        else if(position == "G")
            color = Color.rgb(184, 247, 178)
        else if(position == "C-F")
            color = Color.rgb(237, 175, 173)
        else if(position == "G-F")
            color = Color.rgb(244, 245, 206)
        else
            color = Color.rgb(240, 192, 225)
    }


    fun deletePlayer() {
        viewModelScope.launch { database.delete(igrac) }
    }

    fun share(){
        _sharePlayer.value=true
    }

    fun noShare(){
        _sharePlayer.value=false
    }



}