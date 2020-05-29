package com.maxsch.symmetricguide.ui.main

import android.os.Bundle
import android.view.Menu
import com.google.android.material.snackbar.Snackbar
import com.maxsch.symmetricguide.R
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity

class MainActivity : MvpAppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
}
