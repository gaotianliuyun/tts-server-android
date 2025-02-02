package com.github.jing332.tts_server_android.compose.systts.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.github.jing332.tts_server_android.R
import com.github.jing332.tts_server_android.compose.widgets.AppDialog
import com.github.jing332.tts_server_android.compose.widgets.LabelSlider
import com.github.jing332.tts_server_android.conf.SystemTtsConfig
import java.text.DecimalFormat

@Composable
fun InternalPlayerDialog(onDismissRequest: () -> Unit) {
    var speed by remember { SystemTtsConfig.inAppPlaySpeed }
    var volume by remember { SystemTtsConfig.inAppPlayVolume }
    var pitch by remember { SystemTtsConfig.inAppPlayPitch }
    AppDialog(
        title = { Text(stringResource(id = R.string.systts_use_internal_audio_player)) },
        content = {
            Column {
                LabelSlider(
                    value = speed,
                    onValueChange = {
                        speed = DecimalFormat("#.00").format(it).toFloat()
                    },
                    valueRange = 0.1f..3.0f
                ) {
                    val str = stringResource(id = R.string.label_speed) + speed
                    Text(str)
                    str
                }

                LabelSlider(
                    value = volume,
                    onValueChange = {
                        volume = DecimalFormat("#.00").format(it).toFloat()
                    },
                    valueRange = 0.1f..1.0f
                ) {
                    val str = stringResource(id = R.string.label_volume) + volume
                    Text(str)
                    str
                }

                LabelSlider(
                    value = pitch,
                    onValueChange = {
                        pitch = DecimalFormat("#.00").format(it).toFloat()
                    },
                    valueRange = 0.1f..3.0f
                ) {
                    val str = stringResource(id = R.string.label_pitch) + pitch
                    Text(str)
                    str
                }

            }
        },
        buttons = {
            Row {
                TextButton(
                    enabled = speed != 1f || volume != 1f || pitch != 1f,
                    onClick = {
                        speed = 1f
                        volume = 1f
                        pitch = 1f
                    }) {
                    Text(stringResource(id = R.string.reset))
                }

                TextButton(onClick = onDismissRequest) {
                    Text(stringResource(id = R.string.close))
                }
            }
        }, onDismissRequest = onDismissRequest
    )

}