package com.arcgis.developers.samples.androidapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.esri.arcgisruntime.arcgisservices.LabelDefinition
import com.esri.arcgisruntime.data.ServiceFeatureTable
import com.esri.arcgisruntime.geometry.Point
import com.esri.arcgisruntime.geometry.SpatialReferences
import com.esri.arcgisruntime.layers.FeatureLayer
import com.esri.arcgisruntime.loadable.LoadStatus
import com.esri.arcgisruntime.mapping.ArcGISMap
import com.esri.arcgisruntime.mapping.Basemap
import com.esri.arcgisruntime.mapping.Viewpoint
import com.esri.arcgisruntime.symbology.TextSymbol
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.JsonPrimitive
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val tag = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val map = ArcGISMap(Basemap.createLightGrayCanvas())
        mapView.map = map

        val serviceFeatureTable = ServiceFeatureTable(getString(R.string.congressional_districts))
        val featureLayer = FeatureLayer(serviceFeatureTable)
        map.operationalLayers.add(featureLayer)

        featureLayer.addDoneLoadingListener {
            if (featureLayer.loadStatus == LoadStatus.LOADED) {
                mapView.setViewpointAsync(
                    Viewpoint(Point(-10974490.0, 4814376.0, 0.0, SpatialReferences.getWebMercator()), 40000000.0)
                )
            } else {
                Toast.makeText(this, getString(R.string.error_message) + featureLayer.loadError.message, Toast.LENGTH_LONG)
                    .show()
                Log.e(tag, getString(R.string.error_message) + featureLayer.loadError.message)
            }
        }

        // use red text with white halo for republican district labels
        val republicanTextSymbol = TextSymbol().apply {
            size = 10f
            color = -0x10000
            haloColor = -0x1
            haloWidth = 2f
        }

        // use blue text with white halo for democrat district labels
        val democratTextSymbol = TextSymbol().apply {
            size = 10f
            color = -0xffff01
            haloColor = -0x1
            haloWidth = 2f
        }

        // construct a json label definition
        val jsonDefinition = JsonObject().apply {
            // use a custom label expression combining some of the feature's fields
            val expressionInfo = JsonObject()
            expressionInfo.add("expression",
                JsonPrimitive("\$feature.NAME + \" (\" + left(\$feature.PARTY,1) + \")\\nDistrict \" + \$feature.CDFIPS")
            )
            add("labelExpressionInfo", expressionInfo)
            // position the label in the center of the feature
            add("labelPlacement", JsonPrimitive("esriServerPolygonPlacementAlwaysHorizontal"))
        }

        // create a copy of the json with a custom where clause and symbol only for republican districts
        val republicanJson = jsonDefinition.deepCopy().apply {
            add("where", JsonPrimitive("PARTY = 'Republican'"))
            add("symbol", JsonParser().parse(republicanTextSymbol.toJson()))
        }

        // create a copy of the json with a custom where clause and symbol only for democrat districts
        val democratJson = jsonDefinition.deepCopy().apply {
            add("where", JsonPrimitive("PARTY = 'Democrat'"))
            add("symbol", JsonParser().parse(democratTextSymbol.toJson()))
        }

        // create label definitions from the JSON strings
        val republicanLabelDefinition = LabelDefinition.fromJson(republicanJson.toString())
        val democratLabelDefinition = LabelDefinition.fromJson(democratJson.toString())

        // add the definitions to the feature layer
        featureLayer.labelDefinitions.addAll(Arrays.asList(republicanLabelDefinition, democratLabelDefinition))
        featureLayer.labelDefinitions.add(democratLabelDefinition)

        // enable labels
        featureLayer.isLabelsEnabled = true
    }

    override fun onPause() {
        super.onPause()
        mapView.pause()
    }

    override fun onResume() {
        super.onResume()
        mapView.resume()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.dispose()
    }
}