package su.pank.filmapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.koin.androidx.compose.get
import su.pank.filmapp.R
import su.pank.filmapp.domain.viewmodel.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInScreen(navHostController: NavHostController) {
    val loginViewModel: LoginViewModel = get()

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(20.dp, 0.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_with_title),
            contentDescription = null,
            modifier = Modifier
                .padding(10.dp)
                .padding(10.dp)
                .align(Alignment.CenterHorizontally)
        )
        OutlinedTextField(
            value = loginViewModel.email.value,
            onValueChange = { text -> loginViewModel.email.value = text },
            label = {
                Text(
                    text = "Email"
                )
            },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = loginViewModel.password.value,
            onValueChange = { text -> loginViewModel.password.value = text },
            label = {
                Text(
                    text = "Password"
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp), verticalArrangement = Arrangement.Bottom
    ) {
        Button(onClick = { loginViewModel.login() }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Войти")
        }
        Button(
            onClick = { navHostController.navigate("reg") },
            colors = ButtonDefaults.buttonColors(contentColor = MaterialTheme.colorScheme.secondaryContainer),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Регистрация")
        }
    }


}
