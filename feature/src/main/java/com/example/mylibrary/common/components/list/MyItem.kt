package com.example.mylibrary.common.components.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.example.core.network.Util.Companion.CONT_HTTP
import com.example.core.network.Util.Companion.CONT_HTTPS
import com.example.mylibrary.R
import com.example.mylibrary.model.MyItemCustom

@Composable
fun MyItem(
    modifier: Modifier,
    item: MyItemCustom,
) {
    Box(
        modifier =
            modifier
                .fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AsyncImage(
                modifier =
                    modifier
                        .size(dimensionResource(id = R.dimen.padding_80dp))
                        .fillMaxWidth(),
                contentScale = ContentScale.Crop,
                model = item.thumb.replace(CONT_HTTP, CONT_HTTPS),
                contentDescription = stringResource(R.string.image_item),
            )
            Text(
                text = "$ ${item.price}",
                modifier = modifier.padding(dimensionResource(id = R.dimen.padding_2dp)),
            )
        }
    }
}

@Composable
fun TitleItem(item: MyItemCustom) {
    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_16dp))
                .verticalScroll(rememberScrollState())
                .height(IntrinsicSize.Max),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            item.title,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
fun DescriptItem(
    item: MyItemCustom,
    modifier: Modifier,
) {
    if (item.descrip.isNotBlank()) {
        Card(
            colors =
                CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    contentColor = MaterialTheme.colorScheme.background,
                ),
            modifier =
                modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.padding_16dp))
                    .verticalScroll(rememberScrollState()),
            border = BorderStroke(dimensionResource(id = R.dimen.padding_2dp), Color.Gray),
        ) {
            var visible by remember {
                mutableStateOf(false)
            }
            Column(
                modifier =
                    modifier
                        .navigationBarsPadding()
                        .imePadding()
                        .fillMaxWidth(),
            ) {
                Row(horizontalArrangement = Arrangement.Center) {
                    Column(
                        modifier =
                            Modifier
                                .fillMaxHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.background,
                            modifier =
                                modifier
                                    .fillMaxHeight()
                                    .padding(dimensionResource(id = R.dimen.padding_8dp)),
                        )
                    }
                    Text(
                        text = stringResource(R.string.detail),
                        style = MaterialTheme.typography.titleMedium,
                        modifier =
                            modifier
                                .fillMaxWidth()
                                .padding(dimensionResource(id = R.dimen.padding_8dp))
                                .clickable {
                                    visible = !visible
                                },
                        color = MaterialTheme.colorScheme.background,
                    )
                }
            }
            Divider(
                color = MaterialTheme.colorScheme.background,
                thickness = dimensionResource(id = R.dimen.padding_1dp),
                modifier =
                    modifier
                        .fillMaxWidth(),
            )
            AnimatedVisibility(visible) {
                Text(
                    text = item.descrip,
                    style = MaterialTheme.typography.bodySmall,
                    modifier =
                        modifier
                            .fillMaxSize()
                            .padding(dimensionResource(id = R.dimen.padding_16dp)),
                    color = MaterialTheme.colorScheme.background,
                )
            }
        }
    }
}
