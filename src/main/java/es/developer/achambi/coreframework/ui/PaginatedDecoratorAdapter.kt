package es.developer.achambi.coreframework.ui

import es.developer.achambi.coreframework.databinding.LoadingItemBinding

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.View

import es.developer.achambi.coreframework.R

class PaginatedDecoratorAdapter(private val paginatedInterface: PaginatedInterface )
    : SearchAdapterDecorator<PagePresentation, LoadingHolder>() {


    override fun getLayoutResource(): Int {
        return R.layout.loading_item
    }

    override fun createViewHolder(rootView: View): LoadingHolder {
        val binding : LoadingItemBinding? = DataBindingUtil.bind(rootView)
        return LoadingHolder(binding)
    }

    override fun bindViewHolder(holder: LoadingHolder, item: PagePresentation) {
        holder.binding?.item = item
        if(!item.error){
            paginatedInterface.requestNextPage(item.nextPageIndex)
        }
    }

    override fun getAdapterViewType(): Int {
        return R.id.paginated_item_view_id
    }
}

interface PaginatedInterface {
    fun requestNextPage(nextPageIndex: Int)
}

class LoadingHolder(val binding: LoadingItemBinding?) : RecyclerView.ViewHolder(binding!!.root)
