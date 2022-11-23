package com.dukar.media.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dukar.media.ui.ui.theme.UiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mCheckedState = remember{ mutableStateOf(false)}
            UiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,

                    ) {
                        Greeting(name = "Terminal Monitoring Service",40.sp,FontWeight.Bold)
                        Spacer(modifier = Modifier.height(20.dp))
                        Switch(
                            modifier = Modifier.scale(3f),
                            checked = mCheckedState.value,
                            onCheckedChange = { mCheckedState.value = it },
                            colors = SwitchDefaults.colors(
                                checkedTrackColor = Color.Gray,
                                uncheckedThumbColor = Color.White,
                                uncheckedTrackColor = Color.Gray,
                            ),
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        SmallText(name = "Disconnected",15.sp,FontWeight.Bold,20)
                        Spacer(modifier = Modifier.height(20.dp))
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start,
                        ) {
                            rowUI(title = "Battery Percentage:", value = "40%")
                            rowUI(title = "Battery Temperature:", value = "40-C")
                            rowUI(title = "Network Type:", value = "WIFI")
                            rowUI(title = "Network Strength:", value = "66")
                            rowUI(title = "Current Location:", value = "-1.24556, 32.2345")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, size: TextUnit, fontWeight: FontWeight) {
    Text(name,
        style = TextStyle(
            color= Color.White,
            fontSize = size,
            fontWeight = fontWeight,
        ),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .graphicsLayer(alpha = 0.99f).fillMaxWidth()
            .drawWithCache {
                val brush = Brush.horizontalGradient(listOf(Color.Blue, Color.Cyan))
                onDrawWithContent {
                    drawContent()
                    drawRect(brush, blendMode = BlendMode.SrcAtop)
                }
            }
            .padding(20.dp)
    )
}

@Composable
fun SmallText(name: String, size: TextUnit, fontWeight: FontWeight,padding:Int) {
    Text(name,
        style = TextStyle(
            color= Color.Black,
            fontSize = size,
            fontWeight = fontWeight,
        ),
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(padding.dp)

        )
}

@Composable
fun rowUI(title:String,value:String){
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,){
        SmallText(name = title,15.sp,FontWeight.Bold,10)
        SmallText(name = value,14.sp,FontWeight.Normal,0)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    UiTheme {
        Greeting("Android",12.sp,FontWeight.Medium)
    }
}