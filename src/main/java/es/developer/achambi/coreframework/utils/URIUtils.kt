package es.developer.achambi.coreframework.utils

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import android.provider.OpenableColumns
import android.util.Log
import android.webkit.MimeTypeMap


class URIUtils {
    fun retrieveFileMetadata( context: Context, uri: Uri? ): URIMetadata {
        // The query, since it only applies to a single document, will only return
        // one row. There's no need to filter, sort, or select fields, since we want
        // all fields for one document.
        if(uri == null) {
            return URIMetadata()
        }
        val cursor: Cursor? = context.contentResolver?.
            query( uri, null, null, null, null, null)
        cursor?.use {
            // moveToFirst() returns false if the cursor has 0 rows.  Very handy for
            // "if there's anything to look at, look at it" conditionals.
            if (it.moveToFirst()) {

                // Note it's called "Display Name".  This is
                // provider-specific, and might not necessarily be the file name.
                val displayName: String =
                    it.getString(it.getColumnIndex(OpenableColumns.DISPLAY_NAME))

                val sizeIndex: Int = it.getColumnIndex(OpenableColumns.SIZE)
                // If the size is unknown, the value stored is null.  But since an
                // int can't be null in Java, the behavior is implementation-specific,
                // which is just a fancy term for "unpredictable".  So as
                // a rule, check if it's null before assigning to an int.  This will
                // happen often:  The storage API allows for remote files, whose
                // size might not be locally known.
                val size: String = if (!it.isNull(sizeIndex)) {
                    // Technically the column stores an int, but cursor.getString()
                    // will do the conversion automatically.
                    it.getString(sizeIndex)
                } else {
                    ""
                }
                return URIMetadata(displayName, size, getMimeType(context.contentResolver, uri))
            }
        }
        return URIMetadata()
    }

    private fun getMimeType(contentResolver: ContentResolver?, uri: Uri): String? {
        val mime = MimeTypeMap.getSingleton()
        return mime.getExtensionFromMimeType(contentResolver?.getType(uri))
    }
}

data class URIMetadata(val displayName: String? = "",
                       val size: String? = "",
                       val mimeType: String? = ""): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(displayName)
        parcel.writeString(size)
        parcel.writeString(mimeType)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<URIMetadata> {
        override fun createFromParcel(parcel: Parcel): URIMetadata {
            return URIMetadata(parcel)
        }

        override fun newArray(size: Int): Array<URIMetadata?> {
            return arrayOfNulls(size)
        }
    }

}