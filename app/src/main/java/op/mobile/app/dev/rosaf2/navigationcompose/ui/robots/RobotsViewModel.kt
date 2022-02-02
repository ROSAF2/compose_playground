package op.mobile.app.dev.rosaf2.navigationcompose.ui.robots

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import op.mobile.app.dev.rosaf2.navigationcompose.api.ServiceInstance.retrofitService
import op.mobile.app.dev.rosaf2.navigationcompose.model.Robot

class RobotsViewModel : ViewModel() {
    private val _robots: MutableState<List<Robot>> = mutableStateOf(listOf())
    val robots: State<List<Robot>> get() = _robots

    init {
        viewModelScope.launch {
            _robots.value = retrofitService.getResponse()
        }
    }
}
