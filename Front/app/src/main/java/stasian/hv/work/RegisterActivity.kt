package com.example.myapp

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import stasian.hv.work.api.UserApi
import stasian.hv.work.api.UserCredentials

@Composable
fun RegisterScreen(onRegistrationResult: (Boolean) -> Unit) {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Зарегистрироваться",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Поле ввода логина
            BasicTextField(
                value = login,
                onValueChange = { login = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Gray.copy(alpha = 0.9f), RoundedCornerShape(8.dp))
                    .padding(16.dp),
                textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
                singleLine = true,
                decorationBox = { innerTextField ->
                    Box {
                        if (login.isEmpty()) {
                            Text(
                                text = "Введите логин...",
                                color = Color.Gray,
                                fontSize = 16.sp
                            )
                        }
                        innerTextField()
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Поле ввода пароля
            BasicTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Gray.copy(alpha = 0.9f), RoundedCornerShape(8.dp))
                    .padding(16.dp),
                textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
                singleLine = true,
                decorationBox = { innerTextField ->
                    Box {
                        if (password.isEmpty()) {
                            Text(
                                text = "Введите пароль...",
                                color = Color.Gray,
                                fontSize = 16.sp
                            )
                        }
                        innerTextField()
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Кнопка регистрации
            Button(
                onClick = {
                    coroutineScope.launch {
                        try {
                            Log.d("RegisterScreen", "Attempting to register user: $login")

                            // Отправка запроса на сервер
                            val response = Retrofit.Builder()
                                .baseUrl("http://192.168.1.110:8080/") // Замените на ваш URL
                                .addConverterFactory(GsonConverterFactory.create())
                                .build()
                                .create(UserApi::class.java)
                                .registerUser(UserCredentials(login, password))

                            // Логирование успешного ответа
                            if (response.isSuccessful) {
                                Log.d("RegisterScreen", "Registration successful: ${response.body()}")
                                onRegistrationResult(true)
                            } else {
                                // Логируем ошибку на сервере
                                Log.e("RegisterScreen", "Registration failed: ${response.code()} - ${response.message()}")
                                onRegistrationResult(false)
                            }
                        } catch (e: Exception) {
                            // Логируем исключение
                            Log.e("RegisterScreen", "Error during registration", e)
                            onRegistrationResult(false)
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Зарегистрироваться",
                    color = Color.White
                )
            }
        }
    }
}
