package com.example.mylibrary.common.components.tolbar

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.mylibrary.R
import com.example.mylibrary.theme.MyTestTheme

@Composable
fun ExpandableSearchView(
    searchDisplay: String,
    onSearchDisplayChanged: (String) -> Unit,
    onSearchDisplayClosed: () -> Unit,
    onSearchAction: () -> Unit,
    modifier: Modifier = Modifier,
    onExpandedChanged: (Boolean) -> Unit,
    expanded: Boolean = false,
) {
    Crossfade(
        targetState = expanded,
        modifier =
            modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary),
        label = stringResource(R.string.label_crossfade_top_appbase),
    ) { isSearchFieldVisible ->
        when (isSearchFieldVisible) {
            true ->
                ExpandedSearchView(
                    searchDisplay = searchDisplay,
                    onSearchDisplayChanged = onSearchDisplayChanged,
                    onSearchDisplayClosed = onSearchDisplayClosed,
                    onExpandedChanged = onExpandedChanged,
                    modifier = modifier,
                    onSearchAction = onSearchAction,
                )

            false ->
                CollapsedSearchView(
                    onExpandedChanged = onExpandedChanged,
                    modifier = modifier,
                )
        }
    }
}

@Composable
fun SearchIcon() {
    Icon(
        imageVector = Icons.Default.Search,
        contentDescription = stringResource(R.string.to_bar_search_icon),
        tint = MaterialTheme.colorScheme.background,
    )
}

@Composable
fun CollapsedSearchView(
    onExpandedChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(id = R.dimen.padding_2dp)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(R.string.app_name),
            color = MaterialTheme.colorScheme.background,
            style = MaterialTheme.typography.titleLarge,
            modifier =
                Modifier
                    .padding(start = dimensionResource(id = R.dimen.padding_16dp)),
        )
        IconButton(onClick = { onExpandedChanged(true) }) {
            SearchIcon()
        }
    }
}

@Composable
fun ExpandedSearchView(
    searchDisplay: String,
    onSearchDisplayChanged: (String) -> Unit,
    onSearchDisplayClosed: () -> Unit,
    onExpandedChanged: (Boolean) -> Unit,
    onSearchAction: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current

    val textFieldFocusRequester = remember { FocusRequester() }

    SideEffect {
        textFieldFocusRequester.requestFocus()
    }

    var textFieldValue by remember {
        mutableStateOf(TextFieldValue(searchDisplay, TextRange(searchDisplay.length)))
    }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = {
            onExpandedChanged(false)
            onSearchDisplayClosed()
        }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(R.string.to_bar_search_icon),
                tint = MaterialTheme.colorScheme.background,
            )
        }
        TextField(
            value = textFieldValue,
            onValueChange = {
                textFieldValue = it
                onSearchDisplayChanged(it.text)
            },
            trailingIcon = {
                IconButton(onClick = {
                    focusManager.clearFocus()
                    onSearchAction()
                }) {
                    SearchIcon()
                }
            },
            modifier =
                Modifier
                    .fillMaxWidth()
                    .focusRequester(textFieldFocusRequester)
                    .background(MaterialTheme.colorScheme.primary),
            label = {
                Text(
                    text = stringResource(R.string.menu_search),
                    color = MaterialTheme.colorScheme.background,
                )
            },
            keyboardOptions =
                KeyboardOptions(
                    imeAction = ImeAction.Done,
                ),
            keyboardActions =
                KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                        onSearchAction()
                    },
                ),
            colors =
                TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.tertiary,
                    unfocusedContainerColor = MaterialTheme.colorScheme.primary,
                    cursorColor = MaterialTheme.colorScheme.background,
                    focusedTextColor = MaterialTheme.colorScheme.background,
                    unfocusedTextColor = MaterialTheme.colorScheme.background,
                    focusedIndicatorColor = MaterialTheme.colorScheme.background,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.background,
                    unfocusedLabelColor = MaterialTheme.colorScheme.background,
                    focusedLabelColor = MaterialTheme.colorScheme.background,
                    unfocusedSupportingTextColor = MaterialTheme.colorScheme.background,
                    focusedSupportingTextColor = MaterialTheme.colorScheme.background,
                    unfocusedSuffixColor = MaterialTheme.colorScheme.background,
                    focusedSuffixColor = MaterialTheme.colorScheme.background,
                    unfocusedPrefixColor = MaterialTheme.colorScheme.background,
                    focusedPrefixColor = MaterialTheme.colorScheme.background,
                ),
        )
    }
}

@Preview
@Composable
fun CollapsedSearchViewPreview() {
    MyTestTheme {
        Surface(
            color = MaterialTheme.colorScheme.secondary,
            shadowElevation = dimensionResource(id = R.dimen.padding_9dp),
        ) {
            ExpandableSearchView(
                searchDisplay = "",
                onSearchDisplayChanged = {},
                onSearchDisplayClosed = {},
                onSearchAction = {},
                onExpandedChanged = {},
            )
        }
    }
}

@Preview
@Composable
fun ExpandedSearchViewPreview() {
    MyTestTheme {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.tertiary,
            topBar = {
                ExpandableSearchView(
                    searchDisplay = "",
                    onSearchDisplayChanged = {},
                    onSearchDisplayClosed = {},
                    onSearchAction = {},
                    onExpandedChanged = {},
                )
            },
        ) { padding ->
        }
    }
}
