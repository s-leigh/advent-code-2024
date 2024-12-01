package adventcode

fun String.asLines() = this.split('\n').filterNot { it == "" }
