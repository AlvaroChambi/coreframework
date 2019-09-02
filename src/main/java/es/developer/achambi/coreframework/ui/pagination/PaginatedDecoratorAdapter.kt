package es.developer.achambi.coreframework.ui.pagination

import es.developer.achambi.coreframework.databinding.LoadingItemBinding

import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.View

import es.developer.achambi.coreframework.R
import es.developer.achambi.coreframework.ui.SearchAdapterDecorator

class PaginatedDecoratorAdapter(private val paginatedInterface: PaginatedInterface)
    : SearchAdapterDecorator<PaginatedPresentation, LoadingHolder>() {


    override fun getLayoutResource(): Int {
        return R.layout.loading_item
    }

    override fun createViewHolder(rootView: View): LoadingHolder {
        val binding : LoadingItemBinding? = DataBindingUtil.bind(rootView)
        return LoadingHolder(binding)
    }

    override fun bindViewHolder(holder: LoadingHolder, item: PaginatedPresentation) {
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

class LoadingHolder(val binding: LoadingItemBinding?) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding!!.root)
