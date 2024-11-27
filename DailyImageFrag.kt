package com.example.bitfit

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.humcomp.bitfitpart2.R

class DailyImageFrag : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.daily_img_frag, container, false)
    }

    @SuppressLint("Range")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val paths = mutableListOf<String>()
        val image: ImageView = view.findViewById(R.id.daily_image)

        image.setOnClickListener {
            val db = DBHelper(view.context, null)
            val cursor = db.getImg()

            var pathCount = 0
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    // Assuming the path is stored in the "path" column
                    paths.add(cursor.getString(cursor.getColumnIndex("path")))
                    pathCount++
                }
            }

            if (pathCount > 0) {
                // Select a random image path
                val randRange = (0 until pathCount).random()
                val imagePath = paths[randRange]

                // Set the image URI (assuming it's a valid file path)
                val uri = Uri.parse(imagePath)
                image.setImageURI(uri)
            }
        }
    }
}
