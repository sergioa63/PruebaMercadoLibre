package com.example.mylibrary.common.components.list

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import com.example.mylibrary.R
import com.example.mylibrary.model.MyItemCustom
import com.example.mylibrary.theme.secondaryColorLite

@Composable
fun ListItem(
    modifier: Modifier = Modifier,
    listItems: List<MyItemCustom> = emptyList(),
    goToDetail: (String) -> Unit,
) {
    LazyVerticalGrid(
        modifier = modifier.heightIn(min = dimensionResource(id = R.dimen.padding_250dp)),
        columns = GridCells.Adaptive(dimensionResource(id = R.dimen.padding_150dp)),
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_2dp)),
    ) {
        items(listItems) { item ->
            ItemContainer(
                item,
                goToDetail,
                Modifier.padding(dimensionResource(id = R.dimen.padding_2dp))
                    .height(IntrinsicSize.Max),
            )
        }
    }
}

@Composable
fun ItemContainer(
    item: MyItemCustom,
    goToDetail: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    Card(
        modifier =
            modifier.clickable {
                if (!item.selected) {
                    goToDetail(item.id)
                } else {
                    Toast.makeText(
                        context,
                        R.string.item_add_list,
                        Toast.LENGTH_LONG,
                    ).show()
                }
            }
                .verticalScroll(rememberScrollState())
                .height(dimensionResource(id = R.dimen.padding_250dp)),
        colors =
            CardDefaults.cardColors(
                containerColor =
                    if (item.selected) {
                        secondaryColorLite
                    } else {
                        MaterialTheme.colorScheme.secondary
                    },
            ),
        border =
            BorderStroke(
                dimensionResource(id = R.dimen.padding_2dp),
                MaterialTheme.colorScheme.tertiary,
            ),
    ) {
        MyItem(modifier, item)
        TitleItem(item)
    }
}
