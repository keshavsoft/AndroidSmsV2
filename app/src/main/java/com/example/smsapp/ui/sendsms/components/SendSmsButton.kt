package com.example.smsapp.ui.sendsms.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp

@Composable
fun SendSmsButton(
    sending: Boolean,
    success: Boolean,
    inButtonText:  String ="Send Message",
    onSendClick: () -> Unit
)
{
    val interaction = remember { MutableInteractionSource() }
    val pressed by interaction.collectIsPressedAsState()

    val scale by animateFloatAsState(
        targetValue = if (pressed || sending) 0.95f else 1f,
        label = ""
    )

    FilledTonalButton(
        onClick = onSendClick,
        interactionSource = interaction,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .scale(scale),
        shape = RoundedCornerShape(24.dp)
    ) {
        AnimatedContent(targetState = Pair(sending, success), label = "") { (loading, done) ->
            when {
                loading -> CircularProgressIndicator(
                    strokeWidth = 2.dp,
                    modifier = Modifier.size(20.dp)
                )

                done -> Text("Sent ✓")

                else -> Text(inButtonText)
            }
        }
    }
}
