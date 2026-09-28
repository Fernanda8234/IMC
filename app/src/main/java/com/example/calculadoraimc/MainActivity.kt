package com.example.calculadoraimc

import android.R.attr.text
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadoraimc.ui.theme.CalculadoraIMCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraIMCTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    IMCScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun IMCScreen(modifier: Modifier = Modifier) {

    var altura by remember {
        mutableStateOf(0)
    }

    var peso by remember {
        mutableStateOf(0)
    }

    var colorCard by remember {
        mutableStateOf(Color(0xFF7C7C7C))
    }

    Column(modifier = modifier.fillMaxSize()
    ){
        Column(modifier = Modifier.fillMaxWidth()){
                // -- header --
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .background(color = colorResource(id = R.color.cor_app)),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier
                            .size(80.dp)
                            .padding(vertical = 16.dp),
                        painter = painterResource(
                            R.drawable.bmi
                        ),
                        contentDescription = "IMC"
                    )

                    Text(
                        text = "Calculadora IMC",
                        fontSize = 24.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }

                    // -- formulário --
                    Column(
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 32.dp)
                    ) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp)
                                .offset(y = (-30).dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFFFFFFFF)
                            ),
                            elevation = CardDefaults.cardElevation(4.dp)

//                    shape = CircleShape,
//                    border= BorderStroke(width = 2.dp, color = Color.Black)

                        ) {

                            Spacer(modifier = Modifier.height(15.dp))

                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "Seus dados",
                                color = colorResource(id = R.color.cor_app),
                                fontWeight = FontWeight.W700,
                                fontSize = 26.sp,
                                textAlign = TextAlign.Center
                            )

                            Spacer(modifier = Modifier.height(15.dp))

                            TextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 15.dp),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number
                                ),
                                value = if (altura == 0) "" else altura.toString(),
                                onValueChange = { novaAltura ->
                                    altura = novaAltura.toInt()
                                },
                                label = { Text("Altura") },
                                placeholder = { Text("Digite sua altura em cm") }
                            )

                            Spacer(modifier = Modifier.height(15.dp))

                            // OutlinedTextField() -- talvez substituir o TextField
                            TextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 15.dp),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number
                                ),
                                value = if (peso == 0) "" else peso.toString(),
                                onValueChange = { novoPeso ->
                                    peso = novoPeso.toInt()
                                },
                                label = { Text("Peso") },
                                placeholder = { Text("Digite seu peso") }
                            )

                            Spacer(modifier = Modifier.height(15.dp))

                            Button(
                                modifier = Modifier
                                    .width(300.dp)
                                    .height(50.dp)
                                    .align(Alignment.CenterHorizontally),
                                onClick = {},
                                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.cor_app)),
                            ) {
                                Text(
                                    text = "CALCULAR",
                                    fontSize = 16.sp
                                )

                        }
                    }
                }

                // -- resultado --
                val resultado = peso / ((altura.toDouble()/100) * (altura.toDouble()/100))

                Card(
                    modifier = Modifier
                        .width(330.dp)
                        .height(60.dp)
                        .align(Alignment.CenterHorizontally),
                    colors = CardDefaults.cardColors(
                        containerColor = colorCard
                    )
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        when {
                            altura == 0 || peso == 0 -> {
                                colorCard
                                Text(
                                    text ="Sem calculo",
                                    color = Color.White,
                                    fontSize = 25.sp
                                )
                            }

                            resultado < 18.5 -> {
                                colorCard = Color(0xFFFFC107)
                                Text(String.format("%.2f", resultado))

                                Spacer(modifier = Modifier.width(15.dp))

                                Text("Abaixo do peso")
                            }

                            resultado < 25 -> {
                                colorCard = Color(0xFF4CAF50)
                                Text(String.format("%.2f", resultado))

                                Spacer(modifier = Modifier.width(15.dp))

                                Text("Peso ideal")
                            }

                            resultado < 30 -> {
                                colorCard = Color(0xFFFFC107)
                                Text(String.format("%.2f", resultado))

                                Spacer(modifier = Modifier.width(15.dp))

                                Text("Levemente acima do peso")
                            }

                            resultado < 35 -> {
                                colorCard = Color(0xFFFF9800)
                                Text(String.format("%.2f", resultado))

                                Spacer(modifier = Modifier.width(15.dp))

                                Text("Obesidade grau I")
                            }

                            resultado < 40 -> {
                                colorCard = Color(0xFFF44336)
                                Text(String.format("%.2f", resultado))

                                Spacer(modifier = Modifier.width(15.dp))

                                Text("Obesidade grau II")
                            }

                            else -> {
                                colorCard = Color(0xFFD32F2F)
                                Text(String.format("%.2f", resultado))

                                Spacer(modifier = Modifier.width(15.dp))

                                Text("Obesidade grau III")
                        }
                    }
                }
            }
        }
    }
}