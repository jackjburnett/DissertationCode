// Application package name
package com.jb.sensingapplication

//libraries required for getting usage statistics
import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager

// libraries required for UI and application environment
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jb.sensingapplication.databinding.ActivityMainBinding

// Unsure why AppCompatActivity resolved the startActivity issue
class MainActivity : AppCompatActivity() {

    // Sets the variable types to the necessary  classes, used LateInit for safety (Cookbook and BNR recommendation), these are uninitialised
    private lateinit var binding: ActivityMainBinding
    private lateinit var usageStatsManager: UsageStatsManager

    // The function that runs when the application opens
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // binds the xml elements, initialises binding, sets the view to the binding's root (xml view)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        // sets the content view to the binding's root
        setContentView(view)

        // initialises usageStatsManger as the necessary system service
        usageStatsManager = getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager

        // adds an onclick interaction that looks at the current time, and the time 24 hours ago
        binding.buttonRetrieveStats.setOnClickListener {
            val endTime = System.currentTimeMillis()
            val startTime = endTime - 86400000 // 24 hours in milliseconds (24*60*60*1000)

            // runs the getUsageStatistics function
            val usageStats = getUsageStatistics(startTime, endTime)

            // Converts the list to a string, seperated with new lines, showing package names and time in foreground, and outputs it.
            // This is for testing purposes
            binding.textViewStats.text = usageStats.joinToString("\n") { "${it.packageName}: ${it.totalTimeInForeground} ms" }
        }
    }

    // Function to get usage statistics, takes the start and end time and returns a list of usage statistics
    private fun getUsageStatistics(startTime: Long, endTime: Long): List<UsageStats> {
        // creates a mutable list to add stats to, and sets the default interval to daily, this may be changed if backlogs are created
        val usageStatsList = mutableListOf<UsageStats>()
        val interval = UsageStatsManager.INTERVAL_DAILY

        // Queries the usage stats manager using the start and end time, with a daily interval
        val stats = usageStatsManager.queryUsageStats(interval, startTime, endTime)

        // if stats is not empty, add all the stats to the list, if it is null then usageStatsList stays uninitialised
        if (stats != null) {
            usageStatsList.addAll(stats)
        }

        // Sorts the list by total foreground time, this is done for solely for testing purposes
        usageStatsList.sortByDescending { it.totalTimeInForeground }

        // Returns the usageStatsList
        return usageStatsList
    }
}
