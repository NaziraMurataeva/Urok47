package kg.tutorialapp.weather.storage

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single
import kg.tutorialapp.weather.models.ForeCast


@Dao
interface ForeCastDao {
    @Insert
    fun insert(foreCast: ForeCast) : Completable

    @Update
    fun update(foreCast: ForeCast) : Completable

    @Delete
    fun delete(foreCast: ForeCast) : Completable

    @Query("select * from ForeCast")
    fun getAll(): Single<List<ForeCast>>
}