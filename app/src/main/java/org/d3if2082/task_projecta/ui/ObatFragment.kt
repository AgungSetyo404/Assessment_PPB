package org.d3if2082.task_projecta.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import org.d3if2082.task_projecta.R
//import org.d3if2082.task_projecta.data.SettingDataStore
//import org.d3if2082.task_projecta.data.dataStore
import org.d3if2082.task_projecta.databinding.FragmentObatBinding
import org.d3if2082.task_projecta.network.ApiStat
import org.d3if2082.task_projecta.util.Timer

class ObatFragment : Fragment() {

    private lateinit var binding: FragmentObatBinding
    private lateinit var myAdapter: ObatAdapter
    private var isLinearLayoutManager = true
//    private lateinit var layoutDataStore: SettingDataStore

    private val viewModel: ObatViewModel by lazy {
        ViewModelProvider(requireActivity())[ObatViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentObatBinding.inflate(layoutInflater, container, false)
        myAdapter = ObatAdapter()
            with(binding.recyclerView) {
                addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
                adapter = myAdapter
                setHasFixedSize(true)
            }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        layoutDataStore = SettingDataStore(requireContext().dataStore)
//        layoutDataStore.preferenceFlow.asLiveData()
//            .observe(viewLifecycleOwner, { value ->
//                isLinearLayoutManager = value
//                chooseLayout()
//                activity?.invalidateOptionsMenu()
//            })

        viewModel.getData().observe(viewLifecycleOwner, {
            myAdapter.updateData(it)
        })
        viewModel.getStatus().observe(viewLifecycleOwner, {
            updateProgress(it)
        })
        viewModel.scheduleUpdater(requireActivity().application)
    }

    private fun updateProgress(status: ApiStat) {
        when (status) {
            ApiStat.Loading -> {binding.progressBar.visibility = View.VISIBLE}
            ApiStat.Success -> {binding.progressBar.visibility = View.GONE}
            ApiStat.Failed -> {
                binding.progressBar.visibility = View.GONE
                binding.networkErr.visibility = View.VISIBLE

            }
        }
    }

    private fun chooseLayout() {
        if (isLinearLayoutManager) {
            binding.recyclerView.layoutManager =
                LinearLayoutManager(this.requireContext())
        }else {
            binding.recyclerView.layoutManager =
                GridLayoutManager(this.requireContext(), 2)
        }
    }

    private fun setIcon(menuItem: MenuItem?) {
        if (menuItem == null) return

        menuItem.icon =
            if (isLinearLayoutManager)
                ContextCompat.getDrawable(requireContext(),
                    R.drawable.ic_baseline_grid_on_24)
            else ContextCompat.getDrawable(requireContext(),
                    R.drawable.ic_baseline_list_24)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.layout_menu, menu)
        inflater.inflate(R.menu.history_menu, menu)
        inflater.inflate(R.menu.topr_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.menu_history -> {
                findNavController().navigate(
                    R.id.action_obatFragment_to_historyFragment)
                return true
            }

            R.id.nav_aboutapp -> {
                findNavController().navigate(
                    R.id.action_obatFragment_to_aboutAppFragment
                )
                return true
            }
        }
        return when (item.itemId) {
            R.id.action_switch_layout -> {
                isLinearLayoutManager = !isLinearLayoutManager

//                lifecycleScope.launch {
//                    layoutDataStore.saveLayoutToPreferencesStore(
//                        isLinearLayoutManager, requireContext()
//                    )
//                }

                chooseLayout()
                setIcon(item)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }


    }
}