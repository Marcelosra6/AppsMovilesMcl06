package com.mchi.acta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mchi.acta.ui.theme.actAtheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            actAtheme() {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FormularioUsuario(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun FormularioUsuario(modifier: Modifier = Modifier) {
    var nombre by remember {mutableStateOf("")}
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 300.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        Text("Actividad A con Jetpack Compose")
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = nombre,
            onValueChange = {nuevo -> if (nuevo.length <= 8) nombre = nuevo},
            label = { Text("Ingresa tu nombre ")},
            supportingText = { Text("Aqui pon tu nombre · ${nombre.length}/8")},
            trailingIcon = {
                if(nombre.isNotEmpty()){
                    IconButton(onClick = {nombre = ""}) {
                        Icon(Icons.Default.Clear, contentDescription = "Limpiar")
                    }
                }
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FormularioUsuarioPreview() {
    actAtheme()  {
        FormularioUsuario()
    }
}