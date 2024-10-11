package com.pulsar.vacationplanner.presentation.locationDetails.components


import android.content.Context
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker

@Composable
fun OSMapView(
    context: Context,
    latitude: Double = 0.0,
    longitude: Double = 0.0,
    zoomLevel: Double = 15.0
) {
    Configuration.getInstance()
        .load(context, context.getSharedPreferences("osmdroid", Context.MODE_PRIVATE))

    AndroidView(
        factory = { ctx ->
            org.osmdroid.views.MapView(ctx)
                .apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    setTileSource(TileSourceFactory.MAPNIK)
                    controller.setZoom(zoomLevel)
                    controller.setCenter(GeoPoint(latitude, longitude))

                    // Adding a marker at the provided latitude and longitude
                    val marker = Marker(this)
                    marker.position = GeoPoint(latitude, longitude)
                    marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                    overlays.add(marker)
                }
        },
        update = { mapView ->
            // Optional: Update map properties dynamically, like zoom or marker
            mapView.controller.setZoom(zoomLevel)
            mapView.controller.setCenter(GeoPoint(latitude, longitude))
        }
    )
}
