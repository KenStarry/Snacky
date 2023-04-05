package com.kenstarry.snacky.core.presentation.components

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.kenstarry.snacky.ui.theme.Poppins

//  clickable text parts
@Composable
fun AnnotatedClickableString(
    normalText: String,
    clickableText: String,
    primaryColor: Color,
    onTextClicked: () -> Unit
) {

    val tag = "TAG"

    val annotatedText = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.9f),
                fontFamily = Poppins,
                fontSize = MaterialTheme.typography.bodySmall.fontSize
            )
        ) {
            append(normalText)
        }

        //  making the text clickable
        pushStringAnnotation(
            //  tag which will be provided when you click the text
            tag = tag,
            annotation = tag
        )

        withStyle(
            style = SpanStyle(
                color = primaryColor,
                fontWeight = FontWeight.Bold,
                fontFamily = Poppins,
                fontSize = MaterialTheme.typography.bodySmall.fontSize
            )
        ) {
            append(clickableText)
        }

        //  end of annotation with the current tag
        pop()
    }

    //  clickableText to enable clicking
    ClickableText(
        text = annotatedText,
        onClick = { offset ->
            annotatedText.getStringAnnotations(
                tag = tag,
                start = offset,
                end = offset
            )[0].let {
                onTextClicked()
            }
        }
    )

}