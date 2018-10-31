package com.example.demo.app

import com.example.demo.view.MainView
import tornadofx.*

class MyApp: App(MainView::class, Styles::class) {

    override fun stop() {
        find(MainView::class).sceneView.dispose()
    }

}