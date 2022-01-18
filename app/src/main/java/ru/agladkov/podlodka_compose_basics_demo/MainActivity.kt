package ru.agladkov.podlodka_compose_basics_demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.FragmentActivity
import ru.agladkov.podlodka_compose_basics_demo.old_android.setupOldUI
import ru.agladkov.podlodka_compose_basics_demo.ui.theme.Podlodka_Compose_Basics_DemoTheme

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupOldUI()
    }
}

fun MainActivity.setupCompose() {
    setContent {
        Podlodka_Compose_Basics_DemoTheme {
            // A surface container using the 'background' color from the theme
            Surface(color = MaterialTheme.colors.background) {
                Greeting("Android")
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Podlodka_Compose_Basics_DemoTheme {
        Greeting("Android")
    }
}