package es.developer.achambi.coreframework.ui

import android.support.v7.widget.RecyclerView
import android.view.View

import es.developer.achambi.coreframework.R

class PaginatedDecorator( private val paginatedInterface: PaginatedInterface )
    : SearchAdapterDecorator<PagePresentation, LoadingHolder>() {


    override fun getLayoutResource(): Int {
        return R.layout.loading_item
    }

    override fun createViewHolder(rootView: View): LoadingHolder {
        return LoadingHolder(rootView)
    }

    override fun bindViewHolder(holder: LoadingHolder, item: PagePresentation) {
        paginatedInterface.requestNextPage(item.nextPageIndex)
    }

    override fun getAdapterViewType(): Int {
        return R.id.paginated_item_view_id
    }
}

interface PaginatedInterface {
    fun requestNextPage(nextPageIndex: Int)
}

class LoadingHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

