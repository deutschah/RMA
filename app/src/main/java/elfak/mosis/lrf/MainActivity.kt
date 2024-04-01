package elfak.mosis.lrf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import elfak.mosis.lrf.ui.theme.LRFTheme

class MainActivity : ComponentActivity() {
    // Define the User data class inside the MainActivity or move it outside if it needs to be accessed globally.
    data class User(val name: String, val imageUrl: String)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LRFTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen() // Call MainScreen() here instead of Greeting to show the user interface for adding users and displaying the list.
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var name by remember { mutableStateOf("") }
    val userList = remember { mutableStateListOf<MainActivity.User>() } // Adjusted for User being a nested class

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Enter Name") },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { /* Define action if needed */ })
        )
        Button(onClick = {
            if (name.isNotEmpty()) {
                userList.add(MainActivity.User(name, "https://via.placeholder.com/150"))
                name = "" // Reset name field
            }
        }) {
            Text("Add User")
        }
        LazyColumn {
            items(userList) { user ->
                UserRow(user)
            }
        }
    }
}

@Composable
fun UserRow(user: MainActivity.User) {
    Row(verticalAlignment = Alignment.CenterVertically) {

        Spacer(Modifier.width(8.dp))
        Text(user.name) // Add this to display the user's name next to their image
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LRFTheme {
        MainScreen()
    }
}
