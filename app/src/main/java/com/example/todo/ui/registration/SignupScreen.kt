import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.todo.ui.navigation.NavRoutes
import com.example.todo.ui.registration.SignupViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.montanainc.simpleloginscreen.components.BottomComponent
import com.montanainc.simpleloginscreen.components.CheckboxComponent
import com.montanainc.simpleloginscreen.components.HeadingTextComponent
import com.montanainc.simpleloginscreen.components.MyTextFieldComponent
import com.montanainc.simpleloginscreen.components.NormalTextComponent
import com.montanainc.simpleloginscreen.components.PasswordTextFieldComponent
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
fun SignupScreen(
    navController: NavHostController,
    viewModel: SignupViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val auth = Firebase.auth
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            NormalTextComponent(value = "Hello there,")
            HeadingTextComponent(value = "Create an Account")
            Spacer(modifier = Modifier.height(25.dp))

            Column {
                MyTextFieldComponent(
                    labelValue = "Email",
                    icon = Icons.Outlined.Email,
                    value = viewModel.email,
                    onValueChange = viewModel::updateEmail,
                    error = viewModel.emailError
                )
                Spacer(modifier = Modifier.height(10.dp))
                PasswordTextFieldComponent(
                    labelValue = "Password",
                    icon = Icons.Outlined.Lock,
                    value = viewModel.password,
                    onValueChange = viewModel::updatePassword,
                    error = viewModel.passwordError
                )
                CheckboxComponent(
                    checked = viewModel.acceptedTerms,
                    onCheckedChange = { viewModel.toggleTermsAccepted() },
                    error = viewModel.termsError
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Кнопка регистрации
                Button(
                    onClick = {
                       // if (viewModel.validate()) {
                            scope.launch {
                                Log.d("SignupScreen", "Coroutine started")
                                try {
                                    auth.createUserWithEmailAndPassword(
                                        viewModel.email,
                                        viewModel.password
                                    ).await()

                                    Toast.makeText(
                                        context,
                                        "Registration successful!",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    navController.navigate(NavRoutes.LOGIN) {
                                        popUpTo(NavRoutes.SIGNUP) { inclusive = true }
                                    }
                                } catch (e: Exception) {
                                    Log.e("SignupScreen", "Registration error", e)
                                    Toast.makeText(
                                        context,
                                        "Registration failed: ${e.message}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                       // }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4CAF50),
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Register", fontSize = 18.sp)
                }

                Spacer(modifier = Modifier.height(15.dp))

                // Строка с текстом и кнопкой для перехода к логину
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Already have an account? ")
                    TextButton(
                        onClick = {
                            navController.navigate(NavRoutes.LOGIN) {
                                popUpTo(NavRoutes.SIGNUP) { inclusive = true }
                            }
                        }
                    ) {
                        Text(
                            text = "Login",
                            color = Color(0xFF4CAF50),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}