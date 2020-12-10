package com.uma.basicvalidationwithflows

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine

class MainViewModel : ViewModel() {

    @ExperimentalCoroutinesApi
    private var name = MutableStateFlow("")
    @ExperimentalCoroutinesApi
    private var password = MutableStateFlow("")
    @ExperimentalCoroutinesApi
    private var id = MutableStateFlow("")

    @ExperimentalCoroutinesApi
    val isSubmitButtonEnabled: Flow<Boolean> = combine(name, password, id) { name, password, id ->
        val regexString = "[a-zA-Z]+"
        val isNameCorrect = name.matches(regexString.toRegex())
        val isPasswordCorrect = password.length > 8
        val isUserIdCorrect = id.contains("_")
        return@combine isNameCorrect and isPasswordCorrect and isUserIdCorrect
    }

    @ExperimentalCoroutinesApi
    val isFine : Flow<Array<String>> = combine(name){ name ->
        return@combine name
    }

    @ExperimentalCoroutinesApi
    fun setName(name : String){
        this.name.value = name
    }
    @ExperimentalCoroutinesApi
    fun sePassword(password : String){
        this.password.value = password
    }
    @ExperimentalCoroutinesApi
    fun setUserId(id : String){
        this.id.value = id
    }
}