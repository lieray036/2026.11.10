package tw.edu.pu.csim.tcyang.a20261110

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import tw.edu.pu.csim.tcyang.a20261110.ui.theme._20261110Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _20261110Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CanvasScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CanvasScreen (modifier: Modifier = Modifier) {

    Canvas(
        modifier = modifier.fillMaxSize()
    ){
        //繪製洋紅色的圓, 半徑 100, 圓心 (100,100)
        drawCircle(
            Color.Magenta,
            200f,
            Offset(300f, 600f)
        )
        drawRect(
            Color.Blue,
            Offset(300f, 200f),
            Size(200f, 200f),
            style = Stroke(width = 10f)
        )

    }
}

