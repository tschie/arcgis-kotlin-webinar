package com.example.demo.view

import com.esri.arcgisruntime.geoanalysis.LocationViewshed
import com.esri.arcgisruntime.geoanalysis.Viewshed
import com.esri.arcgisruntime.symbology.ColorUtil
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.paint.Color
import tornadofx.*

class ControlPanel(private val viewshed: LocationViewshed) : View("Control Panel") {

    override val root = gridpane {
        isPickOnBounds = false
        paddingAll = 10.0
        background = Background(BackgroundFill(Color.rgb(0, 0, 0, 0.3), null, null))
        setMaxSize(300.0, 500.0)
        row {
            label("Visible")
            togglebutton(if (viewshed.isVisible) "ON" else "OFF") {
                isSelected = viewshed.isVisible
                selectedProperty().addListener { _, _, newValue ->
                    viewshed.isVisible = newValue
                    text = if (newValue) "ON" else "OFF"
                }
            }
        }
        row {
            label("Frustum")
            togglebutton(if (viewshed.isFrustumOutlineVisibile) "ON" else "OFF") {
                isSelected = viewshed.isFrustumOutlineVisibile
                selectedProperty().addListener { _, _, newValue ->
                    viewshed.setFrustumOutlineVisible(newValue)
                    text = if (newValue) "ON" else "OFF"
                }
            }
        }
        row {
            label("Heading")
            slider(max = 360.0, value = viewshed.heading) {
                isShowTickLabels = true
                valueProperty().onChange {
                    viewshed.heading = value
                }
            }
        }
        row {
            label("Pitch")
            slider(max = 90.0, value = viewshed.pitch) {
                isShowTickLabels = true
                valueProperty().onChange {
                    viewshed.pitch = value
                }
            }
        }
        row {
            label("Horizontal Angle")
            slider(max = 360.0, value = viewshed.horizontalAngle) {
                isShowTickLabels = true
                valueProperty().onChange {
                    viewshed.horizontalAngle = value
                }
            }
        }
        row {
            label("Vertical Angle")
            slider(max = 360.0, value = viewshed.verticalAngle) {
                isShowTickLabels = true
                valueProperty().onChange {
                    viewshed.verticalAngle = value
                }
            }
        }
        row {
            label("Min Distance")
            slider(max = 2000.0, value = viewshed.minDistance) {
                isShowTickLabels = true
                valueProperty().onChange {
                    viewshed.minDistance = value
                }
            }
        }
        row {
            label("Max Distance")
            slider(max = 2000.0, value = viewshed.maxDistance) {
                isShowTickLabels = true
                valueProperty().onChange {
                    viewshed.maxDistance = value
                }
            }
        }
        row {
            label("Visible Color")
            colorpicker(ColorUtil.argbToColor(Viewshed.getVisibleColor()), ColorPickerMode.Button) {
                valueProperty().addListener { _, _, newValue ->
                    Viewshed.setVisibleColor(ColorUtil.colorToArgb(newValue))
                }
            }
        }
        row {
            label("Obstructed Color")
            colorpicker(ColorUtil.argbToColor(Viewshed.getObstructedColor()), ColorPickerMode.Button) {
                valueProperty().addListener { _, _, newValue ->
                    Viewshed.setObstructedColor(ColorUtil.colorToArgb(newValue))
                }
            }
        }
        row {
            label("Frustum Outline Color")
            colorpicker(ColorUtil.argbToColor(Viewshed.getFrustumOutlineColor()), ColorPickerMode.Button) {
                valueProperty().addListener { _, _, newValue ->
                    Viewshed.setFrustumOutlineColor(ColorUtil.colorToArgb(newValue))
                }
            }
        }
    }
}
