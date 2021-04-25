package com.blackpineapple.githubrepoconsumer.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.blackpineapple.githubrepoconsumer.R
import com.blackpineapple.githubrepoconsumer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        if (currentFragment == null) {
            val fragment = RepositoryListFragment()
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment)
                .commit()
        }
    }
}