package com.rojer_ko.stranometr.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.rojer_ko.stranometr.R
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar(view)
    }

    override fun onStart() {
        super.onStart()

        newGame()
    }

    private fun setToolbar(view: View) {
        val toolbar: Toolbar = view.findViewById(R.id.main_toolbar)
        toolbar.title = resources.getString(R.string.toolbar_main_title)
        toolbar.setTitleTextColor(
            ContextCompat.getColor(
                toolbar.context,
                R.color.app_default_white
            )
        )
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        this.setHasOptionsMenu(true)
    }

    private fun newGame() {
        main_new_game_btn.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_currentGameFragment)
        }
    }
}