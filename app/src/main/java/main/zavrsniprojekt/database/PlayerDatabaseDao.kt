package main.zavrsniprojekt.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PlayerDatabaseDao {

    @Insert
    suspend fun insert(player: Player)


    @Query("select * from yours_player")
    suspend fun get():MutableList<Player?>

    @Query("delete from yours_player ")
    suspend fun clear()

    @Query("delete from yours_player  where name=:ime")
    suspend fun delete(ime: String)

}