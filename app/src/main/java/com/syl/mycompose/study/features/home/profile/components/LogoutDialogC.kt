package com.syl.mycompose.study.features.home.profile.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.syl.mycompose.R


/**
 * 退出登录确认弹框
 */
@Composable
fun LogoutDialogC(onDismiss: () -> Unit, confirm: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        text = {
            Text(
                text = stringResource(id = R.string.sure_sign_out),
                style = MaterialTheme.typography.titleMedium
            )
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = stringResource(id = R.string.cancel))
            }
        },
        confirmButton = {
            TextButton(onClick = confirm) {
                Text(text = stringResource(id = R.string.confirm))
            }
        }
    )
}