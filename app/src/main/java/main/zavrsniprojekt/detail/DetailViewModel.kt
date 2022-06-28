package main.zavrsniprojekt.detail

import android.app.Application
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.*
import androidx.room.Room
import kotlinx.coroutines.launch
import main.zavrsniprojekt.R
import main.zavrsniprojekt.database.Player
import main.zavrsniprojekt.database.PlayerDatabase
import main.zavrsniprojekt.database.PlayerDatabaseDao
import main.zavrsniprojekt.network.Data

class DetailViewModel(
    data: Data, val database: PlayerDatabaseDao,
    app: Application
) : AndroidViewModel(app) {
    //private lateinit var database: PlayerDatabaseDao
    val space=" "
    val position="Pozicija: "
    private fun requireContext(): Context {
        return requireContext()
    }

    private val _selectedProperty = MutableLiveData<Data>()
    val selectedProperty: LiveData<Data>
        get() = _selectedProperty

    private val _insertPlayer=MutableLiveData<Boolean>()
    val insertPlayer: LiveData<Boolean>
    get() = _insertPlayer

    var color= 0

    init {
        _selectedProperty.value = data
        if(data.position == "C")
            color = Color.rgb(163, 238, 240)
        else if(data.position == "F")
            color = Color.rgb(225, 230, 225)
        else if(data.position == "G")
            color = Color.rgb(184, 247, 178)
        else if(data.position == "C-F")
            color = Color.rgb(237, 175, 173)
        else if(data.position == "G-F")
            color = Color.rgb(244, 245, 206)
        else
            color = Color.rgb(240, 192, 225)
    }

    fun dodajUbazuClicked() {
        selectedProperty.value?.let { Log.d("greska: ", it.first_name) }
        selectedProperty.value?.let { Log.d("greska: ", it.last_name) }
        selectedProperty.value?.let { Log.d("greska: ", it.team.full_name) }
        selectedProperty.value?.let { Log.d("greska: ", it.position) }


        viewModelScope.launch {
            val lista = database.get()
            var igrac: Boolean = false
            for (i in lista) {
                if (i?.name.toString() == selectedProperty.value?.first_name) {
                    igrac = true;
                }
            }
            if (igrac == false) {
                val player = Player()
                //player.id = 1
                player.name = selectedProperty.value?.first_name
                player.surname = selectedProperty.value?.last_name
                player.currentTeam = selectedProperty.value?.team?.full_name
                player.position = selectedProperty.value?.position
                database.insert(player)
                _insertPlayer.value=true
            }
            else{
                Toast.makeText(getApplication(),"Ovaj igrac vec postoji u bazi",Toast.LENGTH_SHORT).show()

            }
        }


    }
}
