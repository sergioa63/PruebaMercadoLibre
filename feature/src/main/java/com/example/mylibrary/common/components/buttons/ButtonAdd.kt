package com.example.mylibrary.common.components.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.mylibrary.R

/**
 * Compose Boton con icono
 *
 * @param addClick fun con argumentos que retorna Unit, se ejecuta en el onClick de Boton flotante
 *
 * @return Composable Element
 *
 */
@Composable
fun ButtonAddWithIcon(addClick: () -> Unit) {
    ExtendedFloatingActionButton(
        onClick = addClick,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.secondary,
        icon = {
            Image(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = stringResource(R.string.image_shopping_add),
                modifier = Modifier.size(dimensionResource(id = R.dimen.padding_20dp)),
                alignment = Alignment.Center,
            )
        },
        text = {
            Text(
                text = stringResource(R.string.agregado_a_la_lista),
                color = MaterialTheme.colorScheme.secondary,
            )
        },
    )
}
