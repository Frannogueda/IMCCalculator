package com.example.imccalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imccalculator.ui.theme.Gradient
import com.example.imccalculator.ui.theme.IMCCalculatorTheme
import java.util.Locale


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IMCCalculatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    IMCCalculatorApp()
                }
            }
        }
    }
}

@Composable
fun IMCCalculatorApp(modifier: Modifier = Modifier){ // principal
    var weightPersonalInput by remember { mutableStateOf("") }
    var heightPersonalInput by remember { mutableStateOf("") }

    var weightPersonal = weightPersonalInput.toDoubleOrNull()?: 0.0
    var heightPersonal = heightPersonalInput.toDoubleOrNull()?: 0.0

    var pointsOfWeight = pointsCalculater(weightPersonal,heightPersonal)
    val points = pointsOfWeight.toDoubleOrNull()?: 0.0
    val resultText = if (heightPersonalInput.length < 3 || points < 10.0|| points > 100.0 || weightPersonal > 300.0) {
        "0.0"
    } else {
        pointsOfWeight
    }
    Column(
        modifier = modifier
            .background(brush = Gradient),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "IMC",
            fontSize = 50.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = 4.sp,
            color = Color.White,
            style = TextStyle(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFFFFD700),
                        Color(0xFFFF8C00)
                    )
                ),
                shadow = Shadow(
                    color = Color.Black.copy(alpha = 0.4f),
                    offset = Offset(x = 4f, y = 6f),
                    blurRadius = 8f
                )
            )
        )
        HorizontalDivider(
            modifier = Modifier.width(200.dp),
            thickness = 3.dp,
            color = Color.White.copy(alpha = 0.7f)
        )
        Spacer(modifier = Modifier.height(50.dp))
        EditNumberField(
            value = heightPersonalInput,
            onValueChange = {heightPersonalInput = it},
            label = R.string.height,
            keyboardOption = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            suffix = R.string.cm
        )
        Spacer(modifier = Modifier.height(50.dp))
        EditNumberField(
           value = weightPersonalInput,
           onValueChange = {weightPersonalInput = it},
           label = R.string.weight,
           keyboardOption = KeyboardOptions.Default.copy(
               keyboardType = KeyboardType.Number,
               imeAction = ImeAction.Done
           ),
            suffix = R.string.kg
       )
        Text(
            text = "${stringResource(R.string.pointofweight)}$resultText",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(vertical = 12.dp)
                .background(
                    color = Color.Black.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
        TextViewsInfo(
            text = R.string.title_composition,
            descript = R.string.index_imc
        )
        HorizontalDivider(
            modifier = Modifier.width(200.dp),
            thickness = 3.dp,
            color = Color.White.copy(alpha = 0.7f)
        )
        TextViewsInfo(
            text = R.string.lower_weight,
            descript = R.string.range_low
        )
        TextViewsInfo(
            text = R.string.normal,
            descript = R.string.range_normal
        )
        TextViewsInfo(
            text = R.string.excess,
            descript = R.string.range_excess
        )
        TextViewsInfo(
            text = R.string.exceeded,
            descript = R.string.range_exceeded
        )
    }
}

fun pointsCalculater(weightPersonal: Double, heightPersonal: Double):String {
    if (heightPersonal <= 0.0) return "0.0"
    val heightMeters = heightPersonal / 100.0
    val imc = weightPersonal / (heightMeters * heightMeters)
    return String.format(Locale.US, "%.1f", imc)
}

@Composable
private fun EditNumberField(
    modifier: Modifier = Modifier,
    value : String,
    onValueChange:(String) -> Unit,
    @StringRes label: Int,
    keyboardOption: KeyboardOptions,
    @StringRes suffix : Int
){
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = {Text(stringResource(label))},
        suffix = { Text(stringResource(suffix), fontWeight = FontWeight.Bold) },
        singleLine = true,
        keyboardOptions = keyboardOption,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color(0xFFFFFFFF),
            focusedContainerColor = Color(0xFF9D5368),
            focusedLabelColor = Color(0xFFFFFFFF)
        ),
        shape = RoundedCornerShape(16.dp)
    )
}
@Composable
private fun TextViewsInfo(
    modifier : Modifier = Modifier,
    @StringRes text : Int,
    @StringRes descript : Int
){
    Row(
        modifier = modifier
            .padding(20.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
        ) {
        Text(
            text = stringResource(text),
            modifier = Modifier
                .weight(1f),
            color = Color.White
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = stringResource(descript),
            modifier = Modifier
                .weight(1f),
            textAlign = TextAlign.End,
            color = Color.White
        )
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun IMCCalculatorAppPreview(){
    IMCCalculatorTheme() {
        IMCCalculatorApp(modifier = Modifier.fillMaxSize())
    }
}