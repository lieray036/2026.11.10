
package tw.edu.pu.csim.tcyang.a20261110

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Button
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

    // 小小兵的 X、Y 座標
    var minionsX by remember { mutableFloatStateOf(0f) }
    var minionsY by remember { mutableFloatStateOf(0f) }

    // 控制開始與停止
    var isMoving by remember { mutableStateOf(false) }

    // 每秒向右下方移動 10px
    LaunchedEffect(isMoving) {
        while (isMoving) {
            delay(100L)
            minionsX += 10f
            minionsY += 10f
        }
    }

    val minionsImage: ImageBitmap =
        ImageBitmap.imageResource(id = R.drawable.minions)

    Column(
        modifier = modifier.fillMaxSize()
    ) {

        // 開始 / 停止按鈕
        Button(
            onClick = {
                isMoving = !isMoving
            }
        ) {
            Text(
                text = if (isMoving) "停止小小兵" else "開始小小兵"
            )
        }

        Box(
            modifier = Modifier.weight(1f)
        ) {

            Canvas(
                modifier = Modifier.fillMaxSize()
            ) {

                CanvasWidth = size.width
                CanvasHeigh = size.height

                // 紫色圓形
                drawCircle(
                    Color.Magenta,
                    200f,
                    Offset(300f, 600f)
                )

                // 藍色矩形
                drawRect(
                    Color.Blue,
                    Offset(300f, 200f),
                    Size(200f, 200f),
                    style = Stroke(width = 10f)
                )

                // 紅色線條
                drawLine(
                    Color.Red,
                    Offset(200f, 800f),
                    Offset(size.width, size.height),
                    strokeWidth = 10f
                )

                // 小小兵最後繪製，確保在最上層
                translate(minionsX, minionsY) {
                    drawImage(minionsImage)
                }
            }
        }

        Text(
            text = "畫布寬度 $CanvasWidth, 長度 $CanvasHeigh"
        )
    }
}
