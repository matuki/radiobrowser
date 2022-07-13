package br.com.matuki.radiobrowser.shared.extension
import android.net.Uri

fun String?.toUri(): Uri = this?.let { Uri.parse(it) } ?: Uri.EMPTY
