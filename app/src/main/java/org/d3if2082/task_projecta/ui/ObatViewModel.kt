package org.d3if2082.task_projecta.ui

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

//(private val db: ObatDao)

class ObatViewModel : ViewModel() {
    private val data = MutableLiveData<List<Obat>>()

    init {
        data.value = obatData()
//        val data = db.getLastObat()
    }


    private fun obatData(): List<Obat> {
        return listOf(
            Obat("Paracetamol", R.drawable.obat2an, "Work in Progress"),
            Obat("Oralit", R.drawable.obat2an, "Work in Progress"),
            Obat("OBH", R.drawable.obat2an, "Work in Progress"),
            Obat("Analgesik", R.drawable.obat2an, "Work in Progress"),
            Obat("Antasida", R.drawable.obat2an, "Work in Progress"),
            Obat("Vitamin", R.drawable.obat2an, "Work in Progress"),
            Obat("Theopiline", R.drawable.obat2an, "Work in Progress"),
            Obat("Tremenza", R.drawable.obat2an, "Work in Progress"),
            Obat("Lactobion", R.drawable.obat2an, "Work in Progress"),
            Obat("Aciclovir", R.drawable.obat2an, "Work in Progress"),
        )
    }

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