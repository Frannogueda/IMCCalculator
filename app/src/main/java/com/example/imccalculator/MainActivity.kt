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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imccalculator.ui.theme.IMCCalculatorTheme

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
    //dos variables mutables para recordar
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        //verticalArrangement = Arrangement.spacedBy(50.dp)
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        Text(
            text = "IMC", //a seguir metiendo

        )
       EditNumberField(

       )
        Spacer(modifier = Modifier.height(50.dp))
       EditNumberField(

        )
        Text(
            text = "PointOfWeight :"
        )
        TextViewsInfo(

        )
        TextViewsInfo(

        )
        TextViewsInfo(

        )
        TextViewsInfo(

        )
    }
}
@Composable
private fun EditNumberField(
    modifier: Modifier = Modifier,
    //value : String,
    //onValueChange:(String) -> Unit
){
    TextField(
        value = "",
        onValueChange = {""}
    )
}
@Composable
private fun TextViewsInfo(
    modifier: Modifier = Modifier,
    //text: String
){
    Row(
        modifier = modifier
            .padding(20.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "primer text",
        )
        Text(
            text = " Comentario",
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