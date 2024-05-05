package com.manui.myapplication.ui

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.manui.myapplication.R
import com.manui.myapplication.databinding.ActivityMainBinding
import com.manui.myapplication.rest.networkadapter.NetworkResponse

class MainActivity : AppCompatActivity() {

    private val vm: MainViewModel by viewModels()
    lateinit var navController: NavController
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            viewModel = vm
            lifecycleOwner = this@MainActivity
        }
        setContentView(binding.root)

        subscribeUI()
        setupNavigation()
        setupToolbar()
        toolbarBack()
    }


    private fun subscribeUI() {
        vm.getResponse().observe(this, Observer<NetworkResponse<Any, Any>> {
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)

            when (it) {
                is NetworkResponse.NetworkError -> {
                    print("")
                }
                is NetworkResponse.ServerError -> {
                    if (it.code == 401) {
//                        Toast.makeText(
//                            this,
//                            getString(R.string.sessionExpired), Toast.LENGTH_LONG
//                        ).show()
//                        AuthRepository.getInstance().deleteTokens()
//                        Rest.resetData()
//                        viewModelStore.clear()
//                        navController.navigate(R.id.loginFragment)
                    } else {
                        // TODO: Deshacer
                        //navController.navigate(R.id.errorFragment)
                    }
                }
                is NetworkResponse.UnknownError -> {
                    // TODO: Deshacer
                    //navController.navigate(R.id.serverFailFragment)
                }
                is NetworkResponse.Loading -> {
                    window.setFlags(
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                    )
                }
                else -> {
                    //navController.navigate(R.id.serverFailFragment)
                }
            }
        })
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        val appBarConfiguration : AppBarConfiguration = AppBarConfiguration(toolbarTopLevelDestinations().toSet())
        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration)
    }

    override fun onSupportNavigateUp() =
        Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp()

    private fun setupNavigation() {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            vm.showToolbar.value = !toolbarDestinations().contains(destination.id)
        }
    }

    private fun toolbarDestinations() = listOf(
        R.id.splashFragment
    )

    private fun toolbarTopLevelDestinations() = listOf(
        R.id.splashFragment,
        R.id.teamFragment
    )

    fun toolbarBack() {
        binding.toolbar.setNavigationOnClickListener {
            this.onBackPressed()
        }
    }


}