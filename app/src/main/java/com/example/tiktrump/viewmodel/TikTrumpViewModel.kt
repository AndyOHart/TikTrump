package com.example.tiktrump.viewmodel


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.tiktrump.model.TrumpQuote
import com.example.tiktrump.repository.Repository
import com.example.tiktrump.utils.NewResult
import kotlinx.coroutines.launch

class TikTrumpViewModel @ViewModelInject constructor(private val repository: Repository) :
    ViewModel() {

    val trumpQuoteLiveDataList: LiveData<NewResult<List<TrumpQuote>>> = repository.trumpQuotesOldWay

    fun getTrumpQuotes() = viewModelScope.launch {
        repository.trumpQuotesOldWay
    }

    fun removeAllTrumpQuotes() = viewModelScope.launch {
        repository.deleteAllQuotes()
    }

}