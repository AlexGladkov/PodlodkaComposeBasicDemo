package ru.agladkov.podlodka_compose_basics_demo.old_android

import androidx.fragment.app.Fragment
import ru.agladkov.podlodka_compose_basics_demo.MainActivity
import ru.agladkov.podlodka_compose_basics_demo.R
import ru.agladkov.podlodka_compose_basics_demo.old_android.master.MasterFragment

fun MainActivity.setupOldUI() {
    changeScreen(MasterFragment())
}

fun MainActivity.changeScreen(fragment: Fragment) {
    supportFragmentManager
        .beginTransaction()
        .replace(R.id.container, fragment)
        .commit()
}