
package tw.edu.pu.csim.tcyang.a20261110

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.res.imageResource
import kotlinx.coroutines.delay
import tw.edu.pu.csim.tcyang.a20261110.ui.theme._20261110Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            _20261110Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    CanvasScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CanvasScreen(modifier: Modifier = Modifier) {

    var CanvasWidth by remember { mutableFloatStateOf(0f) }
    var CanvasHeigh by remember { mutableFloatStateOf(0f) }

    // 小小兵的水平移動距離
    var minionsX by remember { mutableFloatStateOf(0f) }

    // 每秒向右移動 10px
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000L)
            minionsX += 10f
        }
    }

    val minionsImage: ImageBitmap =
        ImageBitmap.imageResource(id = R.drawable.minions)

    Box(
        modifier = modifier.fillMaxSize()
    ) {

        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {

            CanvasWidth = size.width
            CanvasHeigh = size.height

            val minionsWidth = minionsImage.width
            val minionsHeight = minionsImage.height

            val offsetX = (size.width - minionsWidth) / 2f
            val offsetY = (size.height - minionsHeight) / 2f

            // 繪製小小兵，每秒向右移動 10px
            translate(offsetX + minionsX, offsetY) {
                drawImage(minionsImage)
            }

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

            drawLine(
                Color.Red,
                Offset(200f, 800f),
                Offset(size.width, size.height),
                strokeWidth = 10f
            )
        }

        Text(
            text = "畫布寬度 $CanvasWidth, 長度 $CanvasHeigh"
        )
    }
}
