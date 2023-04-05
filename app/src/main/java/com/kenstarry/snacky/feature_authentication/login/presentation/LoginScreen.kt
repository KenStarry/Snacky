package com.kenstarry.snacky.feature_authentication.login.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kenstarry.snacky.R
import com.kenstarry.snacky.ui.custom.spacing

@Composable
fun LoginScreen(
    mainNavHostController: NavHostController
) {

    val loginScrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .verticalScroll(loginScrollState)
            .padding(MaterialTheme.spacing.medium),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.Start
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = buildAnnotatedString {

                    append("Sna")

                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.primary
                        )
                    ) { append("cky") }
                },
                style = MaterialTheme.typography.titleLarge,
                maxLines = 1,
                textAlign = TextAlign.Center
            )

        }

        //  svg image
        Image(
            painter = painterResource(id = R.drawable.undraw_ice_cream_s_2_rh),
            contentDescription = "Snacky svg image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        //  login text
        Text(
            text = "Login",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
        )

        //  login text field
        TextField(
            value = "",
            onValueChange = {}
        )
    }

}


















