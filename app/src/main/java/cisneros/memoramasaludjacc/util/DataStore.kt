package cisneros.memoramasaludjacc.util


import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "memoramasalud")


val LocalAppContext = staticCompositionLocalOf<Context> { error("No context provided") }


@Composable
fun ProvideAppContext(content: @Composable () -> Unit) {
    val ctx = androidx.compose.ui.platform.LocalContext.current
    CompositionLocalProvider(LocalAppContext provides ctx, content = content)
}