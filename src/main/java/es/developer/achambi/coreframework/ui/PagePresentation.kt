package es.developer.achambi.coreframework.ui

import android.os.Parcel
import android.os.Parcelable
import es.developer.achambi.coreframework.R
import es.developer.achambi.coreframework.ui.presentation.SearchListData

data class PagePresentation(val nextPageIndex: Int,
                       val error: Boolean) : SearchListData, Parcelable{


    constructor(parcel: Parcel) : this(
            parcel.readInt(), parcel.readByte() != 0.toByte())

    override fun getViewType(): Int {
        return R.id.paginated_item_view_id
    }

    override fun getId(): Long {
        return nextPageIndex.toLong()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(nextPageIndex)
        parcel.writeByte(if (error) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PagePresentation> {
        override fun createFromParcel(parcel: Parcel): PagePresentation {
            return PagePresentation(parcel)
        }

        override fun newArray(size: Int): Array<PagePresentation?> {
            return arrayOfNulls(size)
        }
    }
}
