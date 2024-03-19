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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.example.architectureassignment.R
import com.example.architectureassignment.presentation.PhoneViewModel
import com.example.architectureassignment.presentation.ui.theme.PaddingExtraLarge
import com.example.architectureassignment.presentation.ui.theme.PaddingMedium
import com.example.architectureassignment.presentation.ui.utils.noRippleClickable
import org.koin.androidx.compose.koinViewModel

@Composable
fun PhoneScreen(
    modifier: Modifier = Modifier,
    viewModel: PhoneViewModel = koinViewModel()
) {
    val focusManager = LocalFocusManager.current

    val phoneState by remember { viewModel.phoneState }
    val formattedPhone by remember { viewModel.formattedPhone }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = PaddingMedium)
            .noRippleClickable { focusManager.clearFocus() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = phoneState.phone,
            isError = phoneState.isError,
            onValueChange = viewModel::onPhoneChange,
            label = {
                Text(
                    text = stringResource(id = R.string.phone)
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        if (phoneState.isError) {
            Text(
                text = stringResource(id = R.string.incorrectFormat),
                color = MaterialTheme.colorScheme.error,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(PaddingMedium))

        Button(
            onClick = viewModel::onFormatPhone
        ) {
            Text(
                text = stringResource(id = R.string.format)
            )
        }

        Spacer(modifier = Modifier.height(PaddingExtraLarge))

        OutlinedTextField(
            value = formattedPhone,
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
}