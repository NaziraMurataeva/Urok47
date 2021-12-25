package kg.tutorialapp.weather.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kg.tutorialapp.weather.models.ForeCast

@Database(
    entities=[ForeCast::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(ModelConverter::class,CollectionsConverter::class)
abstract class ForeCastDataBase:RoomDatabase() {
    abstract fun forecastDao(): ForeCastDao


    companion object {
        private const val DB_NAME = "forecastDb"

        private var DB: ForeCastDataBase? = null

        fun getInstance(context: Context): ForeCastDataBase {
            if (DB == null) {
                DB = Room.databaseBuilder(
                    context,
                    ForeCastDataBase::class.java,
                    DB_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return DB!!
        }
    }
}