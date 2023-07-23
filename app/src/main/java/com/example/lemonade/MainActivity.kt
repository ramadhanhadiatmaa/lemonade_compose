package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeApp() {
    var current by remember {
        mutableStateOf(1)
    }
    var squeeze by remember {
        mutableStateOf(0)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Lemonade", fontWeight = FontWeight.Bold)
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.White,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding -> Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(MaterialTheme.colorScheme.onTertiaryContainer),
        color = MaterialTheme.colorScheme.background
    ) {
        when (current) {
            1 -> {
                LemonadeImageAndText(
                    label = R.string.tap1,
                    desc = R.string.desc1,
                    img = R.drawable.lemon_tree,
                    onClickImage = { current = 2
                    squeeze = (3..6).random()})
            }
            2 -> {
                LemonadeImageAndText(label = R.string.tap2, desc = R.string.desc2,
                    img = R.drawable.lemon_squeeze,
                    onClickImage = {
                        squeeze--
                        if (squeeze == 0){
                            current = 3
                        }
                    })
            }
            3 -> {
                LemonadeImageAndText(label = R.string.tap3, desc = R.string.desc3, img = R.drawable.lemon_drink,
                    onClickImage = { current = 4 })
            }
            4 -> {
                LemonadeImageAndText(label = R.string.tap4, desc = R.string.desc4, img = R.drawable.lemon_restart,
                    onClickImage = { current = 1})
            }
        }
    }

    }
}

@Composable
fun LemonadeImageAndText(
    label: Int,
    desc: Int,
    img: Int,
    onClickImage: () -> Unit,
    modifier: Modifier = Modifier) {
    Box (modifier = modifier){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(stringResource(label))
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onClickImage) {
                Image(
                    painter = painterResource(img),
                    contentDescription = stringResource(desc)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LemonadePreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}