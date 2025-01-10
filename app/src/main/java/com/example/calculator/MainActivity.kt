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
import androidx.compose.ui.text.TextStyle
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
    if(secondNum == "")return firstNum
    else return (firstNum.toDouble() + secondNum.toDouble()).toString()
}
fun Subtract(firstNum: String, secondNum: String): String {
    if(secondNum == "")return firstNum
    else return (firstNum.toDouble() - secondNum.toDouble()).toString()
}
fun Multiply(firstNum: String, secondNum: String): String {
    if(secondNum == "")return firstNum
    else return (firstNum.toDouble() * secondNum.toDouble()).toString()
}
fun Divide(firstNum: String, secondNum: String): String {
    if(secondNum == "")return firstNum
    else return (firstNum.toDouble() / secondNum.toDouble()).toString()
}
fun Percent(firstNum: String, secondNum: String): String {
    if(secondNum == "")return firstNum
    else return ((firstNum.toDouble() / secondNum.toDouble())*100).toString()
}

fun chkSolution(pNum:String, sNum:String, symbol:Char): String{
    when(symbol) {
        '+' -> return Add(firstNum = pNum, secondNum = sNum)
        '-' -> return Subtract(firstNum = pNum, secondNum = sNum)
        '*' -> return Multiply(firstNum = pNum, secondNum = sNum)
        '/' -> return Divide(firstNum = pNum, secondNum = sNum)
        '%' -> return Percent(firstNum = pNum, secondNum = sNum)
        else -> return ""
    }
}
@Composable
fun CalculatorApp( modifier: Modifier = Modifier) {
    val operators = "+-*/%"
    var priNum by remember { mutableStateOf("") }
    var secNum by remember { mutableStateOf("") }
    var solution by remember { mutableStateOf("") }
    var uInput by remember { mutableStateOf("") }
    var isFirstNum by remember { mutableStateOf(true)}
    var calcOpr by remember { mutableStateOf('=')}
    Column (modifier = Modifier.fillMaxSize()){
        Column(Modifier.fillMaxWidth().fillMaxHeight(0.3f)) {
            TextField(
                textStyle = TextStyle(fontSize = 40.sp),
                value = uInput,
                onValueChange = { newValue -> uInput = newValue },
                modifier = Modifier.fillMaxWidth()
                    .fillMaxSize(0.6f)
                    .focusProperties {
                        canFocus = false
                    },
//                keyboardOptions = KeyboardOptions(keyboardType = Decimal)
            )
            TextField(
                value = solution,
                onValueChange = {  },
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
                        priNum = ""
                        secNum = ""
                        uInput = ""
                        calcOpr = '='
                        isFirstNum = true
                        solution = chkSolution(pNum = priNum, sNum = secNum, symbol = calcOpr)
                    }) {
                    Text(text = "AC", fontSize = textScale, modifier = Modifier.padding(0.dp))
                }
                Button(modifier = Modifier.fillMaxHeight(buttonHeight)
                    .fillMaxWidth(buttonWidth)
                    .weight(1f)
                    .padding(buttonPadding),
                    onClick = {
                        if(isFirstNum)isFirstNum = false
                        else{
                            if(solution.isNotEmpty()){priNum = solution}
                            secNum = ""
                        }
                        uInput = "$uInput%"
                        calcOpr = '%'
                        solution = chkSolution(pNum = priNum, sNum = secNum, symbol = calcOpr)
                    }) {
                    Text(text = "%", fontSize = textScale, modifier = Modifier.padding(0.dp))
                }
                Button(modifier = Modifier.fillMaxHeight(buttonHeight)
                    .fillMaxWidth(buttonWidth)
                    .weight(1f)
                    .padding(buttonPadding),
                    onClick = {
                        if(isFirstNum)priNum = priNum.dropLast(1)
                        else{
                            if(operators.contains(uInput.last()))calcOpr = '=' else {
                                if(!secNum.isEmpty())secNum = secNum.dropLast(1) else {
                                    isFirstNum = true
                                    priNum = priNum.dropLast(1)
                                }
                            }
                        }
                        uInput = uInput.dropLast(1)
                        solution = chkSolution(pNum = priNum, sNum = secNum, symbol = calcOpr)
                    }) {
                    Icon(imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                        contentDescription = "Backspace")
                }
                Button(modifier = Modifier.fillMaxHeight(buttonHeight)
                    .fillMaxWidth(buttonWidth)
                    .weight(1f)
                    .padding(buttonPadding),
                    onClick = {
                        if(isFirstNum)isFirstNum = false
                        else{
                            if(solution.isNotEmpty()){priNum = solution}
                            secNum = ""
                        }
                        uInput = "$uInput/"
                        calcOpr = '/'
                        solution = chkSolution(pNum = priNum, sNum = secNum, symbol = calcOpr)
                    }) {
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
                    onClick = {
                        uInput = uInput + "7"
                        if (isFirstNum) priNum += "7" else secNum += "7"
                        solution = chkSolution(pNum = priNum, sNum = secNum, symbol = calcOpr)
                    }) {
                    Text(text = "7", fontSize = textScale, modifier = Modifier.padding(0.dp))
                }
                Button(modifier = Modifier.fillMaxHeight(buttonHeight)
                    .fillMaxWidth(buttonWidth)
                    .weight(1f)
                    .padding(buttonPadding),
                    onClick = {
                        uInput = uInput + "8"
                        if (isFirstNum) priNum += "8" else secNum += "8"
                        solution = chkSolution(pNum = priNum, sNum = secNum, symbol = calcOpr)
                    }) {
                    Text(text = "8", fontSize = textScale, modifier = Modifier.padding(0.dp))
                }
                Button(modifier = Modifier.fillMaxHeight(buttonHeight)
                    .fillMaxWidth(buttonWidth)
                    .weight(1f)
                    .padding(buttonPadding),
                    onClick = {
                        uInput = uInput + "9"
                        if (isFirstNum) priNum += "9" else secNum += "9"
                        solution = chkSolution(pNum = priNum, sNum = secNum, symbol = calcOpr)
                    }) {
                    Text(text = "9", fontSize = textScale, modifier = Modifier.padding(0.dp))
                }
                Button(modifier = Modifier.fillMaxHeight(buttonHeight)
                    .fillMaxWidth(buttonWidth)
                    .weight(1f)
                    .padding(buttonPadding),
                    onClick = {
                        if(isFirstNum)isFirstNum = false
                        else{
                            if(solution.isNotEmpty()){priNum = solution}
                            secNum = ""
                        }
                        uInput = "$uInput*"
                        calcOpr = '*'
                        solution = chkSolution(pNum = priNum, sNum = secNum, symbol = calcOpr)
                    }) {
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
                    onClick = {
                        uInput = uInput + "4"
                        if (isFirstNum) priNum += "4" else secNum += "4"
                        solution = chkSolution(pNum = priNum, sNum = secNum, symbol = calcOpr)
                    }) {
                    Text(text = "4", fontSize = textScale, modifier = Modifier.padding(0.dp))
                }
                Button(modifier = Modifier.fillMaxHeight(buttonHeight)
                    .fillMaxWidth(buttonWidth)
                    .weight(1f)
                    .padding(buttonPadding),
                    onClick = {
                        uInput = uInput + "5"
                        if (isFirstNum) priNum += "5" else secNum += "5"
                        solution = chkSolution(pNum = priNum, sNum = secNum, symbol = calcOpr)
                    }) {
                    Text(text = "5", fontSize = textScale, modifier = Modifier.padding(0.dp))
                }
                Button(modifier = Modifier.fillMaxHeight(buttonHeight)
                    .fillMaxWidth(buttonWidth)
                    .weight(1f)
                    .padding(buttonPadding),
                    onClick = {
                        uInput = uInput + "6"
                        if (isFirstNum) priNum += "6" else secNum += "6"
                        solution = chkSolution(pNum = priNum, sNum = secNum, symbol = calcOpr)
                    }) {
                    Text(text = "6", fontSize = textScale, modifier = Modifier.padding(0.dp))
                }
                Button(modifier = Modifier.fillMaxHeight(buttonHeight)
                    .fillMaxWidth(buttonWidth)
                    .weight(1f)
                    .padding(buttonPadding),
                    onClick = {
                        if(isFirstNum)isFirstNum = false
                        else{
                            if(solution.isNotEmpty()){priNum = solution}
                            secNum = ""
                        }
                        uInput = "$uInput-"
                        calcOpr = '-'
                        solution = chkSolution(pNum = priNum, sNum = secNum, symbol = calcOpr)
                    }) {
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
                    onClick = {
                        uInput = uInput + "1"
                        if (isFirstNum) priNum += "1" else secNum += "1"
                        solution = chkSolution(pNum = priNum, sNum = secNum, symbol = calcOpr)
                    }) {
                    Text(text = "1", fontSize = textScale, modifier = Modifier.padding(0.dp))
                }
                Button(modifier = Modifier.fillMaxHeight(buttonHeight)
                    .fillMaxWidth(buttonWidth)
                    .weight(1f)
                    .padding(buttonPadding),
                    onClick = {
                        uInput += "2"
                        if (isFirstNum) priNum += "2" else secNum += "2"
                        solution = chkSolution(pNum = priNum, sNum = secNum, symbol = calcOpr)
                    }){
                    Text(text = "2", fontSize = textScale, modifier = Modifier.padding(0.dp))
                }
                Button(modifier = Modifier.fillMaxHeight(buttonHeight)
                    .fillMaxWidth(buttonWidth)
                    .weight(1f)
                    .padding(buttonPadding),
                    onClick = {
                        uInput += "3"
                        if (isFirstNum) priNum += "3" else secNum += "3"
                        solution = chkSolution(pNum = priNum, sNum = secNum, symbol = calcOpr)
                    }) {
                    Text(text = "3", fontSize = textScale, modifier = Modifier.padding(0.dp))
                }
                Button(modifier = Modifier.fillMaxHeight(buttonHeight)
                    .fillMaxWidth(buttonWidth)
                    .weight(1f)
                    .padding(buttonPadding),
                    onClick = {
                        if(isFirstNum)isFirstNum = false
                        else{
                            if(solution.isNotEmpty()){priNum = solution}
                            secNum = ""
                        }
                        uInput = "$uInput+"
                        calcOpr = '+'
                        solution = chkSolution(pNum = priNum, sNum = secNum, symbol = calcOpr)
                    }) {
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
                    onClick = {
                        uInput = uInput + "00"
                        if (isFirstNum) priNum += "00" else secNum += "00"
                        solution = chkSolution(pNum = priNum, sNum = secNum, symbol = calcOpr)
                    }) {
                    Text(text = "00", fontSize = textScale, modifier = Modifier.padding(0.dp))
                }
                Button(modifier = Modifier.fillMaxHeight(buttonHeight)
                    .fillMaxWidth(buttonWidth)
                    .weight(1f)
                    .padding(buttonPadding),
                    onClick = {
                        uInput = uInput + "0"
                        if (isFirstNum) priNum += "0" else secNum += "0"
                        solution = chkSolution(pNum = priNum, sNum = secNum, symbol = calcOpr)
                    }) {
                    Text(text = "0", fontSize = textScale, modifier = Modifier.padding(0.dp))
                }
                Button(modifier = Modifier.fillMaxHeight(buttonHeight)
                    .fillMaxWidth(buttonWidth)
                    .weight(1f)
                    .padding(buttonPadding),
                    onClick = {
                        uInput = uInput + "."
                        if (isFirstNum) priNum += "." else secNum += "."
                        solution = chkSolution(pNum = priNum, sNum = secNum, symbol = calcOpr)
                    }) {
                    Text(text = ".", fontSize = textScale, modifier = Modifier.padding(0.dp))
                }
                Button(modifier = Modifier.fillMaxHeight(buttonHeight)
                    .fillMaxWidth(buttonWidth)
                    .weight(1f)
                    .padding(buttonPadding),
                    onClick = {if(isFirstNum)isFirstNum = false
                    else{
                        if(solution.isNotEmpty()){priNum = solution}
                        secNum = ""
                    }
                        uInput = solution
                        calcOpr = '='
                        solution = chkSolution(pNum = priNum, sNum = secNum, symbol = calcOpr)
                    }) {
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