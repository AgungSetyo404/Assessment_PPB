package org.d3if2082.task_projecta.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if2082.task_projecta.R
import org.d3if2082.task_projecta.db.ObatDao
import org.d3if2082.task_projecta.db.ObatEntity
import org.d3if2082.task_projecta.models.Obat
import org.d3if2082.task_projecta.network.ObatApi

//(private val db: ObatDao)

class ObatViewModel : ViewModel() {
    private val data = MutableLiveData<List<Obat>>()

    init {
        retrieveData()
//        val data = db.getLastObat()
    }

    private fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO) {
            try {
                data.postValue(ObatApi.service.getObat())
            } catch (e: Exception) {
                Log.w("DataProcess", "Failure: ${e.message}")
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
}