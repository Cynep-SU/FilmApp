package su.pank.filmapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import org.koin.androidx.compose.get
import su.pank.filmapp.R
import su.pank.filmapp.domain.viewmodel.RegViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegScreen() {
    val regViewModel: RegViewModel = viewModel()

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(10.dp), verticalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_with_title),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.CenterHorizontally)
        )
        OutlinedTextField(
            value = regViewModel.name.value,
            onValueChange = { text -> regViewModel.name.value = text },
            label = {
                Text(
                    text = "Имя"
                )
            },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true
        )
        OutlinedTextField(
            value = regViewModel.lastName.value,
            onValueChange = { text -> regViewModel.lastName.value = text },
            label = {
                Text(
                    text = "Фамилия"
                )
            },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true
        )
        OutlinedTextField(
            value = regViewModel.email.value,
            onValueChange = { text -> regViewModel.email.value = text },
            label = {
                Text(
                    text = "Email"
                )
            },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true
        )
        OutlinedTextField(
            value = regViewModel.password.value,
            onValueChange = { text -> regViewModel.password.value = text },
            label = {
                Text(
                    text = "Пароль"
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        OutlinedTextField(
            value = regViewModel.secondPassword.value,
            onValueChange = { text -> regViewModel.secondPassword.value = text },
            label = {
                Text(
                    text = "Повторите пароль"
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        val navController: NavHostController = get()
        Button(
            onClick = { regViewModel.reg() },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Зарегестрироваться")
        }
        Button(
            onClick = { navController.navigate("login") },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "У меня уже есть аккаунт")
        }

    }
}