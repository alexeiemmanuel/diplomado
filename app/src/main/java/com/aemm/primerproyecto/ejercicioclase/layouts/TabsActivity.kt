package com.aemm.primerproyecto.ejercicioclase.layouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import com.aemm.primerproyecto.R
import com.google.android.material.tabs.TabLayout

class TabsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabs)

        val tabs = this.findViewById<TabLayout>(R.id.tabs)
        tabs.addTab(tabs.newTab().setText("Football"))
        tabs.addTab(tabs.newTab().setText("Basquetball"))
        tabs.addTab(tabs.newTab().setText("Tennis"))
        //tabs.tabGravity = TabLayout.GRAVITY_START
        tabs.tabGravity = TabLayout.GRAVITY_FILL
    }
}