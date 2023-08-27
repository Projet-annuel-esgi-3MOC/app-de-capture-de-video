package com.esgi.students.camerax.bo

class MediaCategory(var _id: String = "", var name: String = "") {
    companion object {
        fun placeholder(): MediaCategory {
            return MediaCategory("", "")
        }
    }
}
