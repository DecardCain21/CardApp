package com.example.cardapp.features.home

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.os.bundleOf
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.cardapp.R
import com.example.cardapp.core.domain.model.CardInf
import com.example.cardapp.databinding.HomeFragmentBinding
import com.example.cardapp.features.home.state.HomeScreenUiState
import com.example.cardapp.util.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
public class HomeFragment : BindingFragment<HomeFragmentBinding>() {
    private val viewModel: HomeViewModel by viewModels()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): HomeFragmentBinding = HomeFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureHistoryButton()
        configureSearchInput()
        setupObservers()
    }

    private fun setupObservers() {
        // Подписываемся на изменения из ViewModel
        viewModel.viewModelScope.launch {
            viewModel.uiState.collect {
                when (it) {
                    is HomeScreenUiState.Content -> setupContent(it.card)
                    is HomeScreenUiState.Error -> {}
                    HomeScreenUiState.Loading -> {}
                    HomeScreenUiState.NoInternet -> {}
                    HomeScreenUiState.Start -> {}
                }
            }
        }
    }

    private fun setupContent(cardInf: CardInf) {
        with(binding) {
            tvBinValue.text = cardInf.bin
            tvTypeValue.text = cardInf.type
            tvSchemeValue.text = cardInf.scheme
            tvBrandValue.text = cardInf.brand
            tvPrepaidValue.text = cardInf.prepaid.toString()
            tvCountryNumericValue.text = cardInf.countryNumeric
            tvCountryNameValue.text = cardInf.countryName
            tvCountryEmojiValue.text = cardInf.countryEmoji
            tvCountryCurrencyValue.text = cardInf.countryCurrency
            bankNameValue.text = cardInf.bankName
            bankUrlValue.text = cardInf.bankUrl
            bankPhoneValue.text = cardInf.bankPhone
            bankCityValue.text = cardInf.bankCity
        }
    }

    private fun configureSearchInput() {
        binding.etSearch.doOnTextChanged { text, _, _, _ ->
            viewModel.searchDebounce(text?.toString().orEmpty())
        }
        binding.etSearch.setOnEditorActionListener{_,actionId,_->
            if(actionId==EditorInfo.IME_ACTION_DONE||actionId == EditorInfo.IME_ACTION_SEARCH||actionId==EditorInfo.IME_ACTION_GO){
                viewModel.searchDebounce(binding.etSearch.text.toString())
                val inputMethodManager =
                    requireContext().getSystemService(INPUT_METHOD_SERVICE) as? InputMethodManager
                inputMethodManager?.hideSoftInputFromWindow(binding.etSearch.windowToken, 0)
                true
            }else{
                false
            }
        }
    }

    private fun configureHistoryButton() {
        binding.ivHistory.setOnClickListener {
            findNavController().navigate(
                R.id.action_to_details,
                bundleOf("itemId" to 123)
            )
        }
    }
}