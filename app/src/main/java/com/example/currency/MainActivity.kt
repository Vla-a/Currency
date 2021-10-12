package com.example.currency

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.characters.ConnectionType
import com.example.characters.NetworkMonitorUtil
import com.example.currency.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val networkMonitor = NetworkMonitorUtil(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        networkMonitor!!.result = { isAvailable, type ->
            runOnUiThread {
                when (isAvailable) {
                    true -> {
                        when (type) {
                            ConnectionType.Wifi -> {
                                Toast.makeText(this, "Wifi Connection", Toast.LENGTH_SHORT).show()
                            }
                            ConnectionType.Cellular -> {
                                Toast.makeText(this, "Cellular Connection", Toast.LENGTH_SHORT)
                                    .show()
                            }
                            else -> {
                            }
                        }
                    }
                    false -> {
                        Toast.makeText(this, "An error has occurred", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        networkMonitor.register()
    }

    override fun onStop() {
        super.onStop()
        networkMonitor.unregister()
    }
}