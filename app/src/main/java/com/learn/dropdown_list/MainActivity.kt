package com.learn.dropdown_list

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.compose.material3.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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
    var showOnBoarding by rememberSaveable { mutableStateOf(true) }

    Surface(modifier) {
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
    val items = List(100) { "Item $it" }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(modifier = modifier) {
            items(items.size) { index ->
                Greeting(name = items[index])
            }
        }

        Button(
            onClick = navigateToOnboarding,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = Color.White
            )
        ) {
            Text(text = "Onboarding")
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    val extraPadding by animateDpAsState(
        if (expanded) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = ""
    )

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
                    .padding(bottom = extraPadding.coerceAtLeast(0.dp))

            ) {
                Text(text = "Hello")
                Text(text = name, style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold))
            }

            ElevatedButton(
                onClick = { expanded = !expanded },
            ) {
                Text(if (expanded) "Show less" else "Show more")
            }
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "AppContentPreviewDark"
)
@Preview(showBackground = true, widthDp = 320)
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
        DropDownList(navigateToOnboarding = {})
    }
}
