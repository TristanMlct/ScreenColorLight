package com.tristanmlct.screencolorlight

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tristanmlct.screencolorlight.ui.theme.ScreenColorLightTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScreenColorLightTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LightControls()
                }
            }
        }
    }
}

@Composable
fun LightControls(modifier: Modifier = Modifier) {
    var lightOn by remember { mutableStateOf(false) }
    var hue by remember { mutableFloatStateOf(0F) }
    var sat by remember { mutableFloatStateOf(1F) }
    var lig by remember { mutableFloatStateOf(0.5F) }
    val hueGradient = Brush.horizontalGradient(listOf(Color.Red, Color.Yellow, Color.Green, Color.Cyan, Color.Blue, Color.Magenta, Color.Red))
    val satGradient = Brush.horizontalGradient(listOf(Color.hsl(hue, 0F, 0.5F), Color.hsl(hue, 1F, 0.5F)))
    val lumGradient = Brush.horizontalGradient(listOf(Color.hsl(hue, 0.5F, 0F), Color.hsl(hue, 0.5F, 0.5F), Color.hsl(hue, 0.5F, 1F)))

    if (lightOn) {
        Box (
            modifier = modifier
                .fillMaxSize()
                .background(Color.hsl(hue, sat, lig))
                .clickable {
                    lightOn = false
                }
        )
    }
    else {
        Column(
            modifier = modifier
                .fillMaxHeight()
                .background(Color.hsl(0F, 0F, 0.85F)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = modifier.weight(1F))
            Image(
                painter = painterResource(id = R.drawable.light_bulb_svgrepo_com),
                contentDescription = "Light bulb",
                colorFilter = ColorFilter.tint(Color.hsl(hue, sat, lig)),
                modifier = modifier
                    .size(200.dp)
                    .clickable {
                        lightOn = true
                    }
            )
            Spacer(modifier = modifier.weight(1F))
            Text(
                text = "Hue :",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Slider(
                value = hue / 360, onValueChange = { hue = it * 360 },
                modifier = modifier
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(hueGradient)
                    .padding(vertical = 10.dp)
            )
            Text(
                text = "Saturation :",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Slider(
                value = sat, onValueChange = { sat = it },
                modifier = modifier
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(satGradient)
                    .padding(vertical = 10.dp)
            )
            Text(
                text = "Lightness :",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Slider(
                value = lig, onValueChange = { lig = it },
                modifier = modifier
                    .padding(bottom = 20.dp)
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(lumGradient)
                    .padding(vertical = 10.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LightControlsPreview() {
    ScreenColorLightTheme {
        LightControls()
    }
}