package com.example.cardapp.features.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.fragment.findNavController
import com.example.cardapp.R
import com.example.cardapp.databinding.DetailsFragmentBinding
import com.example.cardapp.util.BindingFragment

public class DetailsFragment : BindingFragment<DetailsFragmentBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): DetailsFragmentBinding = DetailsFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemId = requireArguments().getInt("itemId")

        view.findViewById<ComposeView>(R.id.composeView).setContent {
            /*HistoryScreen(
                navController = findNavController(),
                itemId = itemId,
                onBack = { findNavController().popBackStack() }
            )*/
            HistoryRoute(onBack = { findNavController().popBackStack() } )
        }
    }
}