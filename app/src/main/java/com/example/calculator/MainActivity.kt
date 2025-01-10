package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.rounded.MailOutline
import androidx.compose.material3.Button
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
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.KeyboardType.Companion.Decimal
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        Column(Modifier.fillMaxWidth().fillMaxHeight(0.3f)) {
            TextField(
                value = firstNum,
                onValueChange = { newValue -> firstNum = newValue },
                modifier = Modifier.fillMaxWidth()
                    .focusProperties {
                        canFocus = false
                    },
//                keyboardOptions = KeyboardOptions(keyboardType = Decimal)
            )
            TextField(
                value = secondNum,
                onValueChange = { newValue -> secondNum = newValue },
                modifier = Modifier.fillMaxWidth()
                    .focusProperties {
                        canFocus = false
                    },
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType),
                maxLines = 1
            )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement,
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight()
        ) {
            val buttonHeight = 1f
            val buttonWidth = 0.16f
            val buttonPadding = 8.dp
            val rowHeight = 0.04f
            val textScale = 24.sp
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
                    .fillMaxHeight(rowHeight)
                    .weight(1f)){

                Button(modifier = Modifier.fillMaxHeight(buttonHeight)
                    .fillMaxWidth(buttonWidth)
                    .weight(1f)
                    .padding(buttonPadding),
                    onClick = {
                        firstNum = ""
                        secondNum = ""
                    }) {
                    Text(text = "AC", fontSize = textScale, modifier = Modifier.padding(0.dp))
                }
                Button(modifier = Modifier.fillMaxHeight(buttonHeight)
                    .fillMaxWidth(buttonWidth)
                    .weight(1f)
                    .padding(buttonPadding),
                    onClick = {}) {
                    Text(text = "%", fontSize = textScale, modifier = Modifier.padding(0.dp))
                }
                Button(modifier = Modifier.fillMaxHeight(buttonHeight)
                    .fillMaxWidth(buttonWidth)
                    .weight(1f)
                    .padding(buttonPadding),
                    onClick = {}) {
                    Icon(imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                        contentDescription = "Backspace")
                }
                Button(modifier = Modifier.fillMaxHeight(buttonHeight)
                    .fillMaxWidth(buttonWidth)
                    .weight(1f)
                    .padding(buttonPadding),
                    onClick = {}) {
                    Text(text = "/", fontSize = textScale, modifier = Modifier.padding(0.dp))
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
                    .fillMaxHeight(rowHeight)
                    .weight(1f)){

                Button(modifier = Modifier.fillMaxHeight(buttonHeight)
                    .fillMaxWidth(buttonWidth)
                    .weight(1f)
                    .padding(buttonPadding),
                    onClick = {}) {
                    Text(text = "7", fontSize = textScale, modifier = Modifier.padding(0.dp))
                }
                Button(modifier = Modifier.fillMaxHeight(buttonHeight)
                    .fillMaxWidth(buttonWidth)
                    .weight(1f)
                    .padding(buttonPadding),
                    onClick = {}) {
                    Text(text = "8", fontSize = textScale, modifier = Modifier.padding(0.dp))
                }
                Button(modifier = Modifier.fillMaxHeight(buttonHeight)
                    .fillMaxWidth(buttonWidth)
                    .weight(1f)
                    .padding(buttonPadding),
                    onClick = {}) {
                    Text(text = "9", fontSize = textScale, modifier = Modifier.padding(0.dp))
                }
                Button(modifier = Modifier.fillMaxHeight(buttonHeight)
                    .fillMaxWidth(buttonWidth)
                    .weight(1f)
                    .padding(buttonPadding),
                    onClick = {}) {
                    Text(text = "X", fontSize = textScale, modifier = Modifier.padding(0.dp))
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
                    .fillMaxHeight(rowHeight)
                    .weight(1f)){

                Button(modifier = Modifier.fillMaxHeight(buttonHeight)
                    .fillMaxWidth(buttonWidth)
                    .weight(1f)
                    .padding(buttonPadding),
                    onClick = {}) {
                    Text(text = "4", fontSize = textScale, modifier = Modifier.padding(0.dp))
                }
                Button(modifier = Modifier.fillMaxHeight(buttonHeight)
                    .fillMaxWidth(buttonWidth)
                    .weight(1f)
                    .padding(buttonPadding),
                    onClick = {}) {
                    Text(text = "5", fontSize = textScale, modifier = Modifier.padding(0.dp))
                }
                Button(modifier = Modifier.fillMaxHeight(buttonHeight)
                    .fillMaxWidth(buttonWidth)
                    .weight(1f)
                    .padding(buttonPadding),
                    onClick = {}) {
                    Text(text = "6", fontSize = textScale, modifier = Modifier.padding(0.dp))
                }
                Button(modifier = Modifier.fillMaxHeight(buttonHeight)
                    .fillMaxWidth(buttonWidth)
                    .weight(1f)
                    .padding(buttonPadding),
                    onClick = {}) {
                    Text(text = "-", fontSize = textScale, modifier = Modifier.padding(0.dp))
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
                    .fillMaxHeight(rowHeight)
                    .weight(1f)){

                Button(modifier = Modifier.fillMaxHeight(buttonHeight)
                    .fillMaxWidth(buttonWidth)
                    .weight(1f)
                    .padding(buttonPadding),
                    onClick = {}) {
                    Text(text = "1", fontSize = textScale, modifier = Modifier.padding(0.dp))
                }
                Button(modifier = Modifier.fillMaxHeight(buttonHeight)
                    .fillMaxWidth(buttonWidth)
                    .weight(1f)
                    .padding(buttonPadding),
                    onClick = {}) {
                    Text(text = "2", fontSize = textScale, modifier = Modifier.padding(0.dp))
                }
                Button(modifier = Modifier.fillMaxHeight(buttonHeight)
                    .fillMaxWidth(buttonWidth)
                    .weight(1f)
                    .padding(buttonPadding),
                    onClick = {}) {
                    Text(text = "3", fontSize = textScale, modifier = Modifier.padding(0.dp))
                }
                Button(modifier = Modifier.fillMaxHeight(buttonHeight)
                    .fillMaxWidth(buttonWidth)
                    .weight(1f)
                    .padding(buttonPadding),
                    onClick = {}) {
                    Text(text = "+", fontSize = textScale, modifier = Modifier.padding(0.dp))
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
                    .fillMaxHeight(rowHeight)
                    .weight(1f)){

                Button(modifier = Modifier.fillMaxHeight(buttonHeight)
                    .fillMaxWidth(buttonWidth)
                    .weight(1f)
                    .padding(buttonPadding),
                    onClick = {}) {
                    Text(text = "00", fontSize = textScale, modifier = Modifier.padding(0.dp))
                }
                Button(modifier = Modifier.fillMaxHeight(buttonHeight)
                    .fillMaxWidth(buttonWidth)
                    .weight(1f)
                    .padding(buttonPadding),
                    onClick = {}) {
                    Text(text = "0", fontSize = textScale, modifier = Modifier.padding(0.dp))
                }
                Button(modifier = Modifier.fillMaxHeight(buttonHeight)
                    .fillMaxWidth(buttonWidth)
                    .weight(1f)
                    .padding(buttonPadding),
                    onClick = {}) {
                    Text(text = ".", fontSize = textScale, modifier = Modifier.padding(0.dp))
                }
                Button(modifier = Modifier.fillMaxHeight(buttonHeight)
                    .fillMaxWidth(buttonWidth)
                    .weight(1f)
                    .padding(buttonPadding),
                    onClick = {}) {
                    Text(text = "=", fontSize = textScale, modifier = Modifier.padding(0.dp))
                }
            }
            Spacer(modifier = Modifier.fillMaxHeight(0.12f))
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