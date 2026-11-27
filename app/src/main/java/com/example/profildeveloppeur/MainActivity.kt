package com.example.profildeveloppeur

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.profildeveloppeur.ui.theme.ProfilDeveloppeurTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfilDeveloppeurTheme {
                val context = LocalContext.current
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ProfileContainer(
                        onContactClick = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/louisbdt"))
                            context.startActivity(intent)
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
