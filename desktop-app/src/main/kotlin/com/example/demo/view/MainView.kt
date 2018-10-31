package com.example.demo.view

import com.esri.arcgisruntime.geoanalysis.LocationViewshed
import com.esri.arcgisruntime.geometry.Point
import com.esri.arcgisruntime.layers.ArcGISSceneLayer
import com.esri.arcgisruntime.mapping.ArcGISScene
import com.esri.arcgisruntime.mapping.ArcGISTiledElevationSource
import com.esri.arcgisruntime.mapping.Basemap
import com.esri.arcgisruntime.mapping.Surface
import com.esri.arcgisruntime.mapping.view.AnalysisOverlay
import com.esri.arcgisruntime.mapping.view.Camera
import com.esri.arcgisruntime.mapping.view.SceneView
import javafx.geometry.Pos
import javafx.scene.layout.StackPane
import tornadofx.*

class MainView : View("Viewshed App") {

    private val controlPanel: ControlPanel
    val sceneView: SceneView = SceneView()
    private val scene: ArcGISScene = ArcGISScene()
    private val viewshed : LocationViewshed

    init {
        // set the scenes basemap
        scene.basemap = Basemap.createImagery()

        // set the scene
        sceneView.arcGISScene = scene

        // add base surface for elevation data
        val surface = Surface()
        val localElevationImageService = "http://scene.arcgis.com/arcgis/rest/services/BREST_DTM_1M/ImageServer"
        surface.elevationSources.add(ArcGISTiledElevationSource(localElevationImageService))
        scene.baseSurface = surface

        // add a scene layer
        val buildings = "http://tiles.arcgis.com/tiles/P3ePLMYs2RVChkJx/arcgis/rest/services/Buildings_Brest/SceneServer/layers/0"
        val sceneLayer = ArcGISSceneLayer(buildings)
        scene.operationalLayers.add(sceneLayer)

        // add a viewshed
        val analysisOverlay = AnalysisOverlay()
        val location = Point(-4.50, 48.4, 100.0)
        viewshed = LocationViewshed(location, 10.0, 60.0, 90.0, 30.0, 0.0, 500.0)
        analysisOverlay.analyses.add(viewshed)
        sceneView.analysisOverlays.add(analysisOverlay)

        // set the camera
        val camera = Camera(location, 200.0, 20.0, 70.0, 0.0)
        sceneView.setViewpointCamera(camera)

        // create a control panel to set properties on the viewshed
        controlPanel = ControlPanel(viewshed)
    }

    override val root = stackpane {
        setPrefSize(800.0, 400.0)
        add(sceneView)
        add(controlPanel)
        StackPane.setAlignment(controlPanel.root, Pos.TOP_LEFT)
    }
}