package main.zavrsniprojekt.myplayers

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import main.zavrsniprojekt.database.PlayerDatabaseDao

class MyPlayersFactory(
    private val dataSource: PlayerDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyPlayersViewModel::class.java)) {
            return MyPlayersViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}