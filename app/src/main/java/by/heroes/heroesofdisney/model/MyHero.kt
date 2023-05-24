package by.heroes.heroesofdisney.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_heroes")
data class MyHero(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,

    @ColumnInfo(name = "heroID")
    val heroId:Int
)