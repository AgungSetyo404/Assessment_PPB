package org.d3if2082.task_projecta.ui

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.d3if2082.task_projecta.R
import org.d3if2082.task_projecta.databinding.FragmentObatBinding

class ObatFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentObatBinding
    private lateinit var myAdapter: ObatAdapter
    private var isLinearLayoutManager = true

    private val viewModel: ObatViewModel by lazy {
        ViewModelProvider(requireActivity())[ObatViewModel::class.java]
    }


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }

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

        viewModel.getData().observe(viewLifecycleOwner, {
            myAdapter.updateData(it)
        })
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
        inflater.inflate(R.menu.topr_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        if (item.itemId == R.id.nav_aboutapp){
            findNavController().navigate(
                R.id.action_obatFragment_to_aboutAppFragment
            )
            return true
        }
        return when (item.itemId) {
            R.id.action_switch_layout -> {
                isLinearLayoutManager = !isLinearLayoutManager

                chooseLayout()
                setIcon(item)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }


    }
}