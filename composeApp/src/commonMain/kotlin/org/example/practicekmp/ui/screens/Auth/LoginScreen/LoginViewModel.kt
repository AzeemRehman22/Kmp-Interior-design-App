package org.example.practicekmp.ui.screens.Auth.LoginScreen

import androidx.annotation.RequiresFeature
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.example.practicekmp.data.AuthUiState
import practicekmp.composeapp.generated.resources.Res

class LoginViewModel : ViewModel() {

    // State variables for email, password, and password visibility
//    var email by  mutableStateOf("")
//    var password by  mutableStateOf("")
//    var passwordVisible by mutableStateOf(false)

    private val _uiState = mutableStateOf(AuthUiState())
    val uiState: State<AuthUiState>  = _uiState

    fun onEmailChange(newEmail:String){
       _uiState.value = _uiState.value.copy(email = newEmail)
    }

    fun onPasswordChange(newPassword:String) {
        _uiState.value = _uiState.value.copy(password = newPassword)
    }

    fun onPasswordVisibilityChange(show : Boolean) {
        _uiState.value = _uiState.value.copy(isPasswordVisible = show)
    }

    fun onLoginClick() : Boolean {
      var islogin = false
       if( _uiState.value.email == "azeemrehman@gmail.com" && _uiState.value.password == "12345"){
           islogin = true
       }

        return islogin
    }

}

