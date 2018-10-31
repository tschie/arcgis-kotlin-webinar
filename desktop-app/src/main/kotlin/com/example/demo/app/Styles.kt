package com.example.demo.app

import javafx.scene.paint.Color
import tornadofx.*

class Styles : Stylesheet() {
    init {
        label {
            textFill = Color.WHITE
        }
        slider {
            axis {
                tickLabelFill = Color.WHITE
            }
        }
        colorPicker {
            textFill = Color.BLACK
        }
    }
}