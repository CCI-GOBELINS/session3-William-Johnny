package composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Button(label: String, onClick: () -> Unit, modifier: Modifier = Modifier, fontSize: TextUnit = 16.sp) {
    ElevatedButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(text = label, fontSize = fontSize)
    }
}