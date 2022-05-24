package org.d3if2082.task_projecta.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import org.d3if2082.task_projecta.R
import org.d3if2082.task_projecta.databinding.FragmentMainmenuBinding

class MainMenu : Fragment(R.layout.fragment_mainmenu) {

    private lateinit var binding: FragmentMainmenuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainmenuBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonCari.setOnClickListener { it.findNavController().navigate(
            R.id.action_mainMenu_to_obatFragment
        ) }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.topr_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.nav_aboutapp){
            findNavController().navigate(
                R.id.action_mainMenu_to_aboutAppFragment
            )
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}