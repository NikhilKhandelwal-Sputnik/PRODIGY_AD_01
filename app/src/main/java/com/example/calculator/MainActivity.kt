package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.rounded.MailOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.KeyboardType.Companion.Decimal
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorTheme {
                CalcPreview()
            }
        }
    }
}

fun Add(firstNum: String, secondNum: String): String {
    return (firstNum.toDouble() + secondNum.toDouble()).toString()
}
fun Subtract(firstNum: String, secondNum: String): String {
    return (firstNum.toDouble() - secondNum.toDouble()).toString()
}
fun Multiply(firstNum: String, secondNum: String): String {
    return (firstNum.toDouble() * secondNum.toDouble()).toString()
}
fun Divide(firstNum: String, secondNum: String): String {
    return (firstNum.toDouble() / secondNum.toDouble()).toString()
}

@Composable
fun CalculatorApp( modifier: Modifier = Modifier) {
    var firstNum by remember { mutableStateOf("") }
    var secondNum by remember { mutableStateOf("") }
    Column (modifier = Modifier.fillMaxSize()){
        Column(Modifier.fillMaxWidth()) {
            TextField(
                value = firstNum,
                onValueChange = { newValue -> firstNum = newValue },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = Decimal)
            )
            TextField(
                value = secondNum,
                onValueChange = { newValue -> secondNum = newValue },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = Decimal)
            )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth().fillMaxSize()) {
            IconButton(
                content = {
                    Icon(imageVector = Icons.Outlined.Add, contentDescription = "Add")
                },
                onClick = {
                    firstNum = Add(firstNum, secondNum)
                    secondNum = ""
                }
            )
            IconButton(
                content = {
                    Text(text = "-")
                },
                onClick = {
                    firstNum = Subtract(firstNum, secondNum)
                    secondNum = ""
                }
            )
            IconButton(
                content = {
                    Text(text = "x")
                },
                onClick = {
                    firstNum = Multiply(firstNum, secondNum)
                    secondNum = ""
                }
            )
            IconButton(
                content = {
                    Text(text = "/")
                },
                onClick = {
                    firstNum = Divide(firstNum, secondNum)
                    secondNum = ""
                }
            )
        }
    }
}

@Preview(showBackground = true,
    showSystemUi = true
)
@Composable
fun CalcPreview() {
    CalculatorTheme {
        CalculatorApp(modifier = Modifier.fillMaxSize())
    }
}