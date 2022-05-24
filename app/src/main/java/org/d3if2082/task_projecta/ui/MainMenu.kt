package org.d3if2082.task_projecta.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
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

}