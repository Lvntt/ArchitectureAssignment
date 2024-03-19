package com.example.architectureassignment.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.example.architectureassignment.R
import com.example.architectureassignment.presentation.PhonePresenter
import com.example.architectureassignment.presentation.ui.theme.PaddingExtraLarge
import com.example.architectureassignment.presentation.ui.theme.PaddingMedium
import com.example.architectureassignment.presentation.ui.utils.noRippleClickable

class PhoneScreen(
    private val presenter: PhonePresenter
) {

    @Composable
    fun Render(modifier: Modifier = Modifier) {
        var phoneText by remember { mutableStateOf("") }
        var isPhoneTextError by remember { mutableStateOf(false) }
        var formattedPhoneText by remember { mutableStateOf("") }
        val focusManager = LocalFocusManager.current

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = PaddingMedium)
                .noRippleClickable { focusManager.clearFocus() },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = phoneText,
                isError = isPhoneTextError,
                onValueChange = { phoneText = it },
                label = {
                    Text(
                        text = stringResource(id = R.string.phone)
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            if (isPhoneTextError) {
                Text(
                    text = stringResource(id = R.string.incorrectFormat),
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(PaddingMedium))

            Button(
                onClick = {
                    presenter.onFormatPhone(phoneText)
                }
            ) {
                Text(
                    text = stringResource(id = R.string.format)
                )
            }

            Spacer(modifier = Modifier.height(PaddingExtraLarge))

            OutlinedTextField(
                value = formattedPhoneText,
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.formattedPhone)
                    )
                },
                onValueChange = {},
                enabled = false,
                colors = TextFieldDefaults.colors(
                    disabledTextColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }

        DisposableEffect(presenter) {
            presenter.phoneScreen = object : IPhoneScreen {
                override fun showPhoneError() {
                    isPhoneTextError = true
                }

                override fun hidePhoneError() {
                    isPhoneTextError = false
                }

                override fun setFormattedPhone(formattedPhone: String) {
                    formattedPhoneText = formattedPhone
                }
            }

            onDispose {
                presenter.phoneScreen = null
            }
        }
    }
}