package com.rperez.flickersearchapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * The application class for the FlickrSearchApp.
 *
 * This class is annotated with `@HiltAndroidApp` to initialize Hilt's dependency injection
 * framework. Hilt generates and manages the dependency injection graph for the entire application
 * starting from this class.
 */
@HiltAndroidApp
class FlickrSearchApp : Application()
