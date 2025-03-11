package co.feip.fefu2025


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import co.feip.fefu2025.ui.theme.FEFU2025AndroidBaseRepoTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log

class MainActivity : ComponentActivity() {
    private var counter: Int = 0
    private val internetReceiver = InternetReceiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity)
        val textView: TextView = findViewById(R.id.textView)
        val counterTextView: TextView = findViewById(R.id.counterTextView)
        textView.setOnClickListener {
            counter++
            counterTextView.text = counter.toString()
        }

        ContextCompat.registerReceiver(
            this,
            internetReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION),
            ContextCompat.RECEIVER_EXPORTED
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(internetReceiver)
    }



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("count", counter)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        counter = savedInstanceState.getInt("count")
        findViewById<TextView>(R.id.counterTextView).text = "$counter"
    }
}

class InternetReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)
        val isConnected = capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true

        if (isConnected) {
            Log.d("InternetReceiver", "Интернет доступен")
        } else {
            Log.d("InternetReceiver", "Интернет НЕ доступен")
        }


    }
}

