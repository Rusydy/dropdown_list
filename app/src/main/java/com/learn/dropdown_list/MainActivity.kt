package com.learn.dropdown_list

import android.os.Bundle
import androidx.compose.material3.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.learn.dropdown_list.ui.theme.DropDownListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DropDownListTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    AppContent()
                }
            }
        }
    }
}

@Composable
fun OnboardingScreen(
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome to the app")

        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked
        ) {
            Text(text = "Continue")
        }
    }
}

@Composable
fun AppContent(modifier: Modifier = Modifier) {
    var showOnBoarding by remember { mutableStateOf(true) }

    Surface(modifier){
        if (showOnBoarding) {
            OnboardingScreen(onContinueClicked = { showOnBoarding = false })
        } else {
            DropDownList(navigateToOnboarding = { showOnBoarding = true })
        }
    }
}

@Composable
fun DropDownList(
    modifier: Modifier = Modifier,
    navigateToOnboarding: () -> Unit
) {
    val items = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (item in items) {
            Greeting(name = item)
        }

        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = { navigateToOnboarding() }
        ) {
            Text(text = "Back to Onboarding")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppContentPreview() {
    DropDownListTheme {
        AppContent()
    }
}

@Preview(showBackground = true)
@Composable
fun DropDownListPreview() {
    DropDownListTheme {
        DropDownList( navigateToOnboarding = {})
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val expanded = remember { mutableStateOf(false) }
    val extraPadding = if (expanded.value) 48.dp else 0.dp

    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(
            modifier = modifier.padding(24.dp),
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding)

            ) {
                Text(text = "Hello")
                Text(text = name)
            }

            ElevatedButton(
                onClick = { expanded.value = !expanded.value },
            ) {
                Text(if (expanded.value) "Show less" else "Show more")
            }
        }
    }
}

