package com.example.face2face

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.ExperimentalGetImage
import androidx.fragment.app.Fragment
import com.example.face2face.databinding.ActivityMainBinding
import com.example.face2face.result.ResultFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

@ExperimentalGetImage
class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val navView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    replaceFragment(CameraFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_dashboard -> {
                    replaceFragment(ResultFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()
    }
}