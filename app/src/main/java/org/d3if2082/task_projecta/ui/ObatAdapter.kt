package org.d3if2082.task_projecta.ui

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import org.d3if2082.task_projecta.R
import org.d3if2082.task_projecta.databinding.ListObatBinding
import org.d3if2082.task_projecta.models.Obat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController

class ObatAdapter: RecyclerView.Adapter<ObatAdapter.ViewHolder>() {

    private val data = mutableListOf<Obat>()

    fun updateData(newData: List<Obat>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ListObatBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(obat: Obat) = with(binding){
            namaObat.text = obat.namaObat
            imageView.setImageResource(obat.imageid)

            root.setOnClickListener {
                val message = root.context.getString(R.string.load_message, obat.namaObat)
                Toast.makeText(root.context, message, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListObatBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}