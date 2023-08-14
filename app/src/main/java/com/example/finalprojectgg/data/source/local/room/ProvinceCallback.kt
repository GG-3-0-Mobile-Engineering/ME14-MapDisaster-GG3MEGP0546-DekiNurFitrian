package com.example.finalprojectgg.data.source.local.room

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.finalprojectgg.domain.model.listProvince
import com.example.finalprojectgg.utils.Utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Provider

class ProvinceCallback(
    private val provider: Provider<ProvinceDao>
) : RoomDatabase.Callback() {
    private val applicationScope = CoroutineScope(SupervisorJob())
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        applicationScope.launch {
            populateProvince()
        }
    }

    private fun populateProvince() {
        provider.get().insertAllProvince(listProvince.toList().map {
            Utils.DataMapper.provinceModelToProvinceEntity(it)
        })
    }
}