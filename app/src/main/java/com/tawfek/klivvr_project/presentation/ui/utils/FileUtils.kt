package com.tawfek.klivvr_project.presentation.ui.utils

import android.content.Context
import java.io.InputStream
import javax.inject.Inject

class FileUtils @Inject constructor() {
    fun openInputStream(context: Context, fileName: String): InputStream {
        return context.assets.open(fileName)
    }
}