package org.d3if2082.task_projecta.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.d3if2082.task_projecta.R
import org.d3if2082.task_projecta.models.Obat

class ObatViewModel : ViewModel() {
    private val data = MutableLiveData<List<Obat>>()

    init {
        data.value = obatData()
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
    fun getData(): LiveData<List<Obat>> = data
}