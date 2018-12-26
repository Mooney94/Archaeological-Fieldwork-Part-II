package org.wit.hillfort.views.hillfortlist

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import kotlinx.android.synthetic.main.activity_hillfort_list.*
import org.wit.hillfort.R
import org.wit.hillfort.main.MainApp
import org.wit.hillfort.models.HillfortModel
import org.wit.hillfort.views.BaseView

class HillfortListView : BaseView(), HillfortListener, NavigationView.OnNavigationItemSelectedListener{

    lateinit var app: MainApp
    lateinit var presenter: HillfortListPresenter
    lateinit var drawerLayout: DrawerLayout
    lateinit var toggleDrawer: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hillfort_list)
        init(toolbarMain)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.abc_ic_menu_overflow_material)

        app = application as MainApp

        drawerLayout = findViewById(R.id.drawer)
        toggleDrawer = ActionBarDrawerToggle(this,drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggleDrawer)
        toggleDrawer.syncState()

        val navigationView : NavigationView = findViewById(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener(this)

        presenter = initPresenter(HillfortListPresenter(this)) as HillfortListPresenter

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = HillfortAdapter(presenter.getHillforts(), this, app)
        recyclerView.adapter?.notifyDataSetChanged()
        loadHillforts()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_add -> presenter.doAddHillfort()

            R.id.item_map -> presenter.doShowHillfortsMap()

            R.id.item_logout -> presenter.doLogout()

            R.id.item_settings -> presenter.doShowSettings()
        }
        if (toggleDrawer.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_add -> presenter.doAddHillfort()

            R.id.nav_settings -> presenter.doShowSettings()

            R.id.nav_logout -> presenter.doLogout()
        }
        return false
    }

    override fun onHillfortClick(hillfort: HillfortModel) {
        presenter.doEditHillfort(hillfort)
    }

    override fun onHillfortImageClick(image: String) {
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loadHillforts()
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun loadHillforts() {
        recyclerView.adapter = HillfortAdapter(app.currentUser.copy().hillforts, this, app)
        recyclerView.adapter?.notifyDataSetChanged()
    }
}