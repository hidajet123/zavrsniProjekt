package main.zavrsniprojekt.infoplayer

import android.view.Menu
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import main.zavrsniprojekt.database.PlayerDatabaseDao
import java.lang.IllegalArgumentException

class InfoPlayerFactory(
    private val database: PlayerDatabaseDao,
    private val name: String,
    private val surname: String,
    private val team: String,
    private val position: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InfoPlayerViewModel::class.java)) {
            return InfoPlayerViewModel(database, name, surname, team, position) as T
        }
        throw IllegalArgumentException("Pogresan ViewMode")
    }
}