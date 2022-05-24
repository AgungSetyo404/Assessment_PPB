package org.d3if2082.task_projecta.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3if2082.task_projecta.R
import org.d3if2082.task_projecta.databinding.FragmentObatBinding

//// TODO: Rename parameter arguments, choose names that match
//// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"
//
///**
// * A simple [Fragment] subclass.
// * Use the [Obat2Fragment.newInstance] factory method to
// * create an instance of this fragment.
// */

class ObatFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentObatBinding
    private lateinit var myAdapter: ObatAdapter

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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData().observe(viewLifecycleOwner, {
            myAdapter.updateData(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.topr_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.nav_aboutapp){
            findNavController().navigate(
                R.id.action_obatFragment_to_aboutAppFragment
            )
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}