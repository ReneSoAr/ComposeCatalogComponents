package com.example.composecatalogcomponents

import android.graphics.Paint.Align
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun MyConfirmationDialog(
    show: Boolean,
    onDismiss: () -> Unit
){
    if (show){
        Dialog(onDismissRequest = { onDismiss }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                MyTitleDialog(text = "Phone ringtone", modifier = Modifier.padding(24.dp))

                Divider(modifier = Modifier .fillMaxWidth(), color = Color.LightGray)

                var status by remember { mutableStateOf("") }
                MyradioButtonList(status, {status = it})

                Divider(modifier = Modifier .fillMaxWidth(), color = Color.LightGray)

                Row(modifier = Modifier .align(Alignment.End)) {
                    TextButton(onClick = {  }) {
                        Text(text = "Cancel")
                    }
                    
                    TextButton(onClick = {}) {
                        Text(text = "Ok")
                    }

                }

            }
        }
    }

}

@Composable
fun MyCustomDialog(
    show: Boolean,
    onDismiss: () -> Unit
) {
    if (show) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                MyTitleDialog(text = "Set backup account")
                AcountItems(email = "andaACagar@gmail.com", drawable = R.drawable.avatar)
                AcountItems(email = "AMLO@Gmail.com", drawable = R.drawable.avatar )
                AcountItems(email = "Añadir nueva cuenta", drawable = R.drawable.add)
            }
        }
    }
}

@Composable
fun MySimpleCustomDialog(
    show: Boolean,
    onDismiss: () -> Unit
) {
    if (show) {
        Dialog(
            onDismissRequest = { onDismiss() },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        ) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(24.dp)
                    .fillMaxWidth()

            ) {
                Text(text = "Chinga tu madre")
            }
        }

    }
}

@Composable
fun MyDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if (show) {
        AlertDialog(
            onDismissRequest = { onDismiss() },//se llama cuando el usuario cliquea fuera del cuadro
            title = { Text(text = "A huevo") },
            text = { Text(text = "A huevo apestas y en la cama te me recuestas y puto si me contestas") },

            confirmButton = {//Cuando el usuario cliquea el boton de confirmar
                TextButton(onClick = { onConfirm() }) {
                    Text(text = "Sobres")
                }
            },
            dismissButton = {//Cuando cliequea el boton de descartar/rechazar
                TextButton(onClick = { onDismiss() }) {
                    Text(text = "Nel prro")
                }
            }
        )
    }
}

//se usan en custom dialog
@Composable
fun MyTitleDialog(text: String, modifier: Modifier = Modifier.padding(12.dp)) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = text,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            modifier = modifier
        )
    }
}

@Composable
fun AcountItems(email: String, @DrawableRes drawable: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = drawable),
            contentDescription = "Avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(40.dp)
                .clip(CircleShape)
        )
        Text(text = email, fontSize = 14.sp, color = Color.Gray, modifier = Modifier.padding(8.dp))

    }

}