package org.d3if2082.task_projecta.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if2082.task_projecta.R
import org.d3if2082.task_projecta.db.ObatDao
import org.d3if2082.task_projecta.db.ObatEntity
import org.d3if2082.task_projecta.models.Obat
import org.d3if2082.task_projecta.network.ApiStat
import org.d3if2082.task_projecta.network.ObatApi
import org.d3if2082.task_projecta.network.UpdateWorker
import java.util.concurrent.TimeUnit

//(private val db: ObatDao)

class ObatViewModel : ViewModel() {
    private val data = MutableLiveData<List<Obat>>()
    private val status = MutableLiveData<ApiStat>();


    init {
        retrieveData()
//        val data = db.getLastObat()
    }

    private fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO) {
           status.postValue(ApiStat.Loading)
            try {
                data.postValue(ObatApi.service.getObat())
                status.postValue(ApiStat.Success)
            } catch (e: Exception) {
                Log.w("DataProcess", "Failure: ${e.message}")
                status.postValue(ApiStat.Failed)
            }
        }
    }

//    private fun obatData(): List<Obat> {
//        return listOf(
//            Obat("Paracetamol", R.drawable.obat2an, "Work in Progress"),
//            Obat("Oralit", R.drawable.obat2an, "Work in Progress"),
//            Obat("OBH", R.drawable.obat2an, "Work in Progress"),
//            Obat("Analgesik", R.drawable.obat2an, "Work in Progress"),
//            Obat("Antasida", R.drawable.obat2an, "Work in Progress"),
//            Obat("Vitamin", R.drawable.obat2an, "Work in Progress"),
//            Obat("Theopiline", R.drawable.obat2an, "Work in Progress"),
//            Obat("Tremenza", R.drawable.obat2an, "Work in Progress"),
//            Obat("Lactobion", R.drawable.obat2an, "Work in Progress"),
//            Obat("Aciclovir", R.drawable.obat2an, "Work in Progress"),
//        )
//    }

//    fun obatData(namaObat: String){
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                val dataObat = ObatEntity(
//                    namaObat = namaObat
//                )
//                db.insert(dataObat)
//            }
//        }
//    }



    fun getData(): LiveData<List<Obat>> = data
    fun getStatus(): LiveData<ApiStat> = status

    fun scheduleUpdater(app:Application) {
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(30000, TimeUnit.MILLISECONDS)
            .build()

        WorkManager.getInstance(app).enqueueUniqueWork(
            "updater",
            ExistingWorkPolicy.REPLACE,
            request
        )
    }
}