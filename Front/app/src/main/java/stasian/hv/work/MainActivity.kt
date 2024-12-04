package stasian.hv.work

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapp.ColorPicker
import stasian.hv.work.ui.theme.FrontTheme
import com.example.myapp.RegisterScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Проверяем, был ли успешный переход после регистрации
        val registrationStatus = intent.getStringExtra("registration_status")

        setContent {
            FrontTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (registrationStatus != "success") {
                        // Если регистрация не успешна, показываем экран регистрации
                        RegisterScreen(onRegistrationResult = { success ->
                            if (success) {
                                // После успешной регистрации показываем экран с колор пикером
                                Toast.makeText(this@MainActivity, "Регистрация прошла успешно!", Toast.LENGTH_SHORT).show()
                                setContent {
                                    ColorPicker() // Показываем экран с выбором цвета
                                }
                            } else {
                                // Показываем ошибку, если регистрация не прошла
                                Toast.makeText(this@MainActivity, "Ошибка регистрации", Toast.LENGTH_SHORT).show()
                            }
                        })
                    } else {
                        // Если регистрация успешна, показываем экран с колор пикером
                        ColorPicker()
                    }
                }
            }
        }
    }
}
