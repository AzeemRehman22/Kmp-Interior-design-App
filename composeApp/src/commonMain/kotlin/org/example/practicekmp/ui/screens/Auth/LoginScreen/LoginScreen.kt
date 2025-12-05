package org.example.practicekmp.screens.Auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.example.practicekmp.data.AuthUiState
import org.example.practicekmp.ui.screens.Auth.LoginScreen.LoginViewModel
import org.example.practicekmp.ui.theme.primaryDark
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import practicekmp.composeapp.generated.resources.Logo_icon
import practicekmp.composeapp.generated.resources.Res
import practicekmp.composeapp.generated.resources.loginHead
import practicekmp.composeapp.generated.resources.signUpBtn
import practicekmp.composeapp.generated.resources.submitBtn

@Composable
fun LoginScreen(
    onHome: () -> Unit,
    onSignup: () -> Unit,
    loginViewModel: LoginViewModel
) {

    val uistate = loginViewModel.uiState.value

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(20.dp)
    ) {

        //Icon Image
        Image(
            painterResource(Res.drawable.Logo_icon),
            contentDescription = "",
            modifier = Modifier.size(150.dp),

        )
        //heading
        Text(stringResource(Res.string.loginHead), fontSize = 24.sp, fontWeight = FontWeight.Bold, color = primaryDark)
        Spacer(modifier = Modifier.height(20.dp))
        //textField for id or number
        // Email Input
        OutlinedTextField(
            value = uistate.email,
            onValueChange = loginViewModel::onEmailChange,
            label = { Text("Email Address") },
            placeholder = { Text("example@mail.com") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        //textField for id or number

        Spacer(modifier = Modifier.height(16.dp))

        // Password Input
        var passwordVisible by remember { mutableStateOf(false) }

        OutlinedTextField(
            value = uistate.password,
            onValueChange = loginViewModel::onPasswordChange,
            label = { Text("Password") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            visualTransformation = if (uistate.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image =
                    if (uistate.isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                IconButton(onClick = { loginViewModel.onPasswordVisibilityChange(!uistate.isPasswordVisible)
                }) {
                    val image = if (uistate.isPasswordVisible) {
                        Icons.Default.Visibility
                    } else {
                        Icons.Default.VisibilityOff
                    }
                    Icon(imageVector = image, contentDescription = null)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        //Text("FORGOT PASSWORD",fontSize = 18.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.End)

        //Button
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = onHome, modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                stringResource(Res.string.submitBtn),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )
        }

        Text(
            stringResource(Res.string.signUpBtn),
            fontSize = 22.sp,
            modifier = Modifier.padding(20.dp).clickable { onSignup() },
            color =
                primaryDark
        )
    }

}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        onHome = {},
        onSignup = {},
        loginViewModel = LoginViewModel()
    )
}

@Preview (
    showBackground = true
)

@Composable
fun rememberAuthState(): Boolean {
    val isLoggedIn = remember { mutableStateOf(false) }
    // Here you would read from your persistent storage (DataStore/SharedPreferences)
    // For now, we'll use a simple remembered state
    return isLoggedIn.value
}




