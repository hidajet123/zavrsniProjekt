package main.zavrsniprojekt.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import main.zavrsniprojekt.detail.DetailViewModel

@Entity(tableName = "yours_player")
data class Player (
@PrimaryKey(autoGenerate = true)
    var id : Long=0L,
@ColumnInfo(name="name")
    var name: String? = null,
@ColumnInfo(name="lastName")
    var surname: String? = null,
@ColumnInfo(name="currentTeam")
    var currentTeam: String? = null,
@ColumnInfo(name="position")
    var position: String? = null

)