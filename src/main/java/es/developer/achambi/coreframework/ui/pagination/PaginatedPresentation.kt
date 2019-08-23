package es.developer.achambi.coreframework.ui.pagination

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import es.developer.achambi.coreframework.R
import es.developer.achambi.coreframework.ui.presentation.SearchListData

data class PaginatedPresentation(val nextPageIndex: Int,
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

    companion object CREATOR : Parcelable.Creator<PaginatedPresentation> {
        override fun createFromParcel(parcel: Parcel): PaginatedPresentation {
            return PaginatedPresentation(parcel)
        }

        override fun newArray(size: Int): Array<PaginatedPresentation?> {
            return arrayOfNulls(size)
        }
    }
}

class PaginatedBuilder(private val context: Context) {


    fun buildPageInfoError()
            :ArrayList<PaginatedPresentation> {
        val list = ArrayList<PaginatedPresentation>()
        list.add(PaginatedPresentation(0, true))
        return list
    }


    fun<D> buildPageInfo(paginatedData: PaginatedData<D>)
            : ArrayList<PaginatedPresentation> {
        val list = ArrayList<PaginatedPresentation>()
        if( !paginatedData.endPage ) {
            list.add(PaginatedPresentation(paginatedData.nextPageIndex, false))
        }
        return list
    }
}
