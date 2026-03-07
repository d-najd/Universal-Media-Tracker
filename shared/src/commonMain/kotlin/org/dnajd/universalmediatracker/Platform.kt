package org.dnajd.universalmediatracker

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform