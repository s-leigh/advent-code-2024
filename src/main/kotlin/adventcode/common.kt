package adventcode

fun String.lines() = this.split('\n').filterNot { it == "" }
