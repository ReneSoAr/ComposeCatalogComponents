package com.example.composecatalogcomponents

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecatalogcomponents.ui.theme.CheckInfo
import com.example.composecatalogcomponents.ui.theme.ComposeCatalogComponentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCatalogComponentsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    var show by rememberSaveable { mutableStateOf(false) }
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Button(onClick = { show = true }) {
                            Text(text = "Cuenta")
                        }
                        MyConfirmationDialog(
                            show = show,
                            onDismiss = { show = false }
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun getOptions(titles: List<String>): List<CheckInfo> {
    return titles.map {
        var status by rememberSaveable { mutableStateOf(false) }
        CheckInfo(
            title = it,
            selected = status,
            onCheckedChange = { myNewStatus -> status = myNewStatus }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeCatalogComponentsTheme {
        MyCheckedWithText()
    }
}

//------------------------------------Textos--------------------------------------------------------
@Composable
//Textos: permite pintar en pantalla textos
fun MyText() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Esto es un ejemplo XD")
        Text(text = "Ejemplo con cambio de color", color = Color.Blue)
        Text(text = "Ejemplo con estilo de grosor de texto", fontWeight = FontWeight.ExtraBold)
        Text(text = "Otro estilo de grosor de texto", fontWeight = FontWeight.ExtraLight)
        Text(
            text = "Cambio de familia de fuente",
            style = TextStyle(fontFamily = FontFamily.Cursive)
        )
        Text(
            text = "Text con una linea en medio",
            style = TextStyle(textDecoration = TextDecoration.LineThrough)
        )
        Text(
            text = "Texto con linea de abajo",
            style = TextStyle(textDecoration = TextDecoration.Underline)
        )
        Text(
            text = "Texto con combinacioin de estilos de decoracion",
            style = TextStyle(
                textDecoration = TextDecoration.combine(
                    listOf(TextDecoration.LineThrough, TextDecoration.Underline)
                )
            )
        )
        Text(
            text = "Ejemplo de tamaño de texto",
            fontSize = 30.sp
        )

    }
}

@Composable
fun MyTextField() {
    var myText by rememberSaveable { mutableStateOf("") }
    TextField(
        value = myText,
        onValueChange = { myText = it }
    )
}

@Composable/*la funcion tiene un campo de texto que su valor va a ser igual a la variable myText que es de estado
        mutable y persistente ya que no se borra lo que almacena al reconstruirse
        * en la funcion lambda onValueChange la variable myText es igual a una comprobacion en la que dice,
        si el it contiene una a se remplkeza por un campo vacio si no
        *  myText es igual al it que es lo que contiene lo que el usuairo esta ingresando*/
fun MyTextFieldAdvance() {
    var myText by rememberSaveable { mutableStateOf("") }
    TextField(value = myText,
        onValueChange = {
            myText = if (it.contains("a")) {
                it.replace("a", "")
            } else {
                it
            }
        }, label = { Text(text = "Introduce tu nombre") })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextFieldOutlined() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Escribe aqui")
        var myText by rememberSaveable { mutableStateOf("") }
        OutlinedTextField(
            value = myText,
            onValueChange = { myText = it },
            label = { Text(text = "Aqui pendejo") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Magenta, unfocusedBorderColor = Color.Blue
            )
        )
    }
}

@Composable
fun TextFieldStateHosting(name: String, onValueChanged: (String) -> Unit) {
    TextField(value = name, onValueChange = { onValueChanged(it) })
}

//----------------------------------------Botones------------------------------------------------
@Composable
fun MyButtonExample() {
    var enabled by rememberSaveable { mutableStateOf(true) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Button(
            onClick = { enabled = false }, enabled = enabled, colors = ButtonDefaults.buttonColors(
                containerColor = Color.Magenta, contentColor = Color.Blue
            ), border = BorderStroke(5.dp, Color.Green)
        ) {
            Text(text = "Mierda")

        }

        OutlinedButton(
            onClick = { enabled = false },//es lo que se hara al presionar el boton
            enabled = enabled,
            modifier = Modifier.padding(top = 8.dp), //Le da un margen interno superior de 8
            colors = ButtonDefaults.buttonColors(//Permite acceder a los parametros para cambiar los colores del boton
                containerColor = Color.Magenta, //cambia el color de fondo del boton
                contentColor = Color.Blue//Cambia el color de la funete o contenido del boton
            ),
            border = BorderStroke(
                5.dp, Color.Green
            )// crea un borde de 5dp y cambia el color del borde a verde

        ) {
            Text(text = "Press")
        }

        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Press")//No necesariamente requiere de un texto, solamente quita todo borde
        }
    }
}

//--------------------------------------Imagenes____________________________________________
@Composable
fun MyImage() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),//Sirve insertar la imagen desde donde se encuentra
        contentDescription = "Ejemplo de imagenes",//Es para insertar una bree descripsion
        alpha = 0.5f//Controla la opacidad de la imagen
    )
}

@Composable
fun MyImageAdvance() {
    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Ejemplo imagen avanzada",
            modifier = Modifier
                .clip(CircleShape)
                .border(5.dp, Color.Red, CircleShape)
            //Le da una forma circular a la imagen, le agrega un borde circular de 5dp de grosor
            //de color Rojo y tambien de forma circular
        )
    }

}

@Composable
fun MyIcon() {
    Icon(
        imageVector = Icons.Rounded.Star,
        contentDescription = "Icono de estrella",
        tint = Color.Magenta
    )
}

//-------------------------------------ProgressBar---------------------------------------------
@Composable
fun MyProgressBar() {
    var showLoading by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        if (showLoading) {
            CircularProgressIndicator(color = Color.Red, strokeWidth = 3.dp)
            LinearProgressIndicator(
                modifier = Modifier.padding(top = 32.dp),
                color = Color.Red,
                trackColor = Color.Green
            )
        }
        Button(onClick = { showLoading = !showLoading }) {
            Text(text = "Cargar Perfil")
        }


    }
}

@Composable
fun MyProgressBarAdvance() {
    var progressStatus by rememberSaveable { mutableStateOf(0f) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LinearProgressIndicator(progress = progressStatus)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(onClick = {
                if (progressStatus <= 1) {
                    progressStatus += 0.1f
                }
            }) {
                Text(text = "Incrementar")
            }
            Button(onClick = {
                if (progressStatus > 0) {
                    progressStatus -= 0.1f
                }
            }) {
                Text(text = "Reducir")
            }
        }

    }
}

//---------------------------------------Control de seleccion--------------------------------
@Composable
fun MySwitch() {
    var state by rememberSaveable { mutableStateOf(true) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Switch(
            checked = state,
            onCheckedChange = { state = !state },
            enabled = true,
            colors = SwitchDefaults.colors(
                uncheckedThumbColor = Color.Red,
                checkedThumbColor = Color.Blue,
                uncheckedTrackColor = Color.Black,
                checkedTrackColor = Color.Cyan


            )
        )
    }
}

@Composable
fun MyCheckBox() {
    var state by rememberSaveable { mutableStateOf(true) }
    Checkbox(
        checked = state, onCheckedChange = { state = !state }, colors = CheckboxDefaults.colors(
            checkedColor = Color.Red, uncheckedColor = Color.Green, checkmarkColor = Color.Black
        )
    )
}

@Composable
fun MyCheckedWithText() {
    var state by rememberSaveable { mutableStateOf(true) }
    Row(modifier = Modifier.padding(8.dp)) {
        Checkbox(checked = state, onCheckedChange = { state = !state })
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Ejemplo 1")
    }
}

@Composable
fun MyCheckedWithTextCompleted(checkInfo: CheckInfo) {
    Row(modifier = Modifier.padding(8.dp)) {
        Checkbox(
            checked = checkInfo.selected,
            onCheckedChange = { checkInfo.onCheckedChange(!checkInfo.selected) })
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = checkInfo.title)
    }
}

//Indeterminacion, tiene 3 estados, seleccionado, no seleccionado he indeterminado
@Composable
fun MyTriStatusCheckbox() {
    var status by rememberSaveable { mutableStateOf(ToggleableState.Off) }
    TriStateCheckbox(state = status, onClick = {
        status = when (status) {
            ToggleableState.On -> ToggleableState.Off
            ToggleableState.Off -> ToggleableState.Indeterminate
            ToggleableState.Indeterminate -> ToggleableState.On
        }
    })
}

@Composable
fun MyRadioButton() {

    RadioButton(
        selected = true, onClick = { /*TODO*/ }, colors = RadioButtonDefaults.colors(
            selectedColor = Color.Red,
            unselectedColor = Color.Yellow,
            disabledSelectedColor = Color.Green
        )
    )
}

@Composable
fun MyradioButtonList(name: String, onItemSelected: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row() {
            RadioButton(selected = name == "Llorar", onClick = { onItemSelected("Llorar") })
            Text(text = "Llorar")
        }
        Row() {
            RadioButton(selected = name == "Quejarse", onClick = { onItemSelected("Quejarse") })
            Text(text = "Quejarse")
        }
        Row() {
            RadioButton(selected = name == "Ira", onClick = { onItemSelected("Ira") })
            Text(text = "Tener un ataque de ira")
        }
        Row() {
            RadioButton(selected = name == "Sufrir", onClick = { onItemSelected("Sufrir") })
            Text(text = "Acostarte y sufrir")
        }

    }
}

@Composable
fun MyCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = Color.Cyan,
            contentColor = Color.Red
        ),
        border = BorderStroke(5.dp, Color.Green),


        ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Ejemplo 1")
            Text(text = "Ejemplo 2")
            Text(text = "Ejemplo 3")
            Text(text = "Ejemplo 4")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MYBadgeBox() {
    BadgedBox(
        badge = {
            Badge(
                containerColor = Color.Green,
                contentColor = Color.Red
            ) { Text(text = "100") }
        },
        Modifier.padding(16.dp)
    ) {
        Icon(imageVector = Icons.Default.Star, contentDescription = "")
    }
}

@Composable
fun MyDivider() {
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp), color = Color.Red
    )

}

@Composable
fun MyDropDownMenu() {
    var selectedText by rememberSaveable { mutableStateOf("") }
    var expanded by rememberSaveable { mutableStateOf(false) }
    var desserts = listOf("Helado", "Chocolate", "Cafe", "Fruta", "Natillas", "Chilaquiles")

    Column(modifier = Modifier.padding(20.dp)) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .clickable { expanded = true }
                .fillMaxWidth())
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        modifier = Modifier.fillMaxWidth()
    ) {
        desserts.forEach { dessert ->
            DropdownMenuItem(
                text = { Text(text = dessert) },
                onClick = {
                    expanded = false
                    selectedText = dessert
                })
        }
    }

}