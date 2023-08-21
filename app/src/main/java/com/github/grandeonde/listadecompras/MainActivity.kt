package com.github.grandeonde.listadecompras

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.grandeonde.listadecompras.ui.theme.ListaDeComprasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListaDeComprasTheme() {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}


@Composable
fun ListaCheia(name: String, quant: Int, desc: String ) {

    val expanded = remember { mutableStateOf(false)}

    val extraPadding = if (expanded.value) 48.dp else 0.dp

    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(modifier = Modifier
                .weight(1f)
                .padding(bottom = extraPadding)) {
                Text(text = name)
                Text(if (expanded.value) "Quantidade: $quant" else " ")
                Text(if (expanded.value) "$desc" else " ")
            }
            Button(
                onClick = { expanded.value = !expanded.value }
            ) {
                Text(if (expanded.value) "Ver Menos" else "Ver Mais")
            }
        }
    }
}

data class ItemCompra(val nome: String, val quant: Int, val desc: String) {

}

@Composable
fun MyApp(
    modifier: Modifier = Modifier,
    listacompra: List<ItemCompra> = listOf(

        ItemCompra(
            nome="Batata",
            quant=30,
            desc="Tuberculo em que seu uso foi extremamente utila nos paises nordicos, sendo uma iguaria para culinarias diversas"),

        ItemCompra(
            nome="Macarrão",
            quant=2,
            desc="Massa de origem Italiana que serve para fazer diversos pratos e é usada na culinaria "),

        ItemCompra(
            nome="Sopa Em Lata",
            quant=5,
            desc="Para uma refeição mais rapida e geralmente usada para se livrar de uma gripe"),

        ItemCompra(
            nome="Caldo Knor",
            quant=2,
            desc="Geralmente usado para melhorar a sopa"),

        ItemCompra(
            nome="Pão",
            quant=6,
            desc="Massa fermentada que é comum no dia a dia dos brasileiros")),

) {
    Column(modifier = modifier.padding(vertical = 4.dp)) {
        for (name in listacompra) {
            ListaCheia(name = name.nome,
                        quant = name.quant,
                        desc = name.desc)
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    ListaDeComprasTheme() {
        MyApp(
        )
    }
}

