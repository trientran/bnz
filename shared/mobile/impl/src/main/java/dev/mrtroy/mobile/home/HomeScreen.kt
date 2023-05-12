package dev.mrtroy.mobile.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import dev.mrtroy.compose.loadingscrim.LoadingScrim
import dev.mrtroy.compose.scaffold.BasicScaffold
import dev.mrtroy.compose.snackbar.genericErrorMessage
import kotlinx.collections.immutable.ImmutableList
import dev.mrtroy.strings.R as strings_R

@Composable
fun HomeScreen(
    schools: ImmutableList<HomeState.School>?,
    error: HomeState.Error?,
    isLoading: Boolean,
    modifier: Modifier = Modifier,
) {
    BasicScaffold(
        title = {
            Text(
                text = stringResource(strings_R.string.app_name),
                style = TextStyle(color = Color.Blue)
            )
        },
        content = { padding ->
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(color = Color.White)
                    .padding(padding)
                    .padding(16.dp)
            ) {
                LazyColumn {
                    schools?.let {
                        if (it.isEmpty()) {
                            item {
                                Text(
                                    text = stringResource(strings_R.string.empty_error_message),
                                    style = TextStyle(color = Color.Red)
                                )
                            }
                        } else {
                            items(
                                items = schools,
                                key = { school -> school.schoolId }
                            ) { school ->
                                SchoolItem(
                                    modifier = Modifier
                                        .padding(vertical = 4.dp)
                                        .testTag(school.schoolId.toString()),
                                    schoolId = school.schoolId,
                                    schoolName = school.schoolName,
                                    phoneNumber = school.phoneNumber,
                                    emailAddress = school.emailAddress,
                                )
                            }
                        }
                    }
                }
                error?.let {
                    Text(
                        text = genericErrorMessage(it.exception),
                        style = TextStyle(color = Color.Red),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                LoadingScrim(isLoading = isLoading)
            }
        })
}

@Composable
private fun SchoolItem(
    schoolId: Int,
    schoolName: String,
    phoneNumber: String?,
    emailAddress: String?,
    modifier: Modifier = Modifier,
) {
    Card(modifier = modifier) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text(text = schoolId.toString())
            Text(text = schoolName)
            phoneNumber?.let { Text(text = it) }
            emailAddress?.let { Text(text = it) }
        }
    }
}
