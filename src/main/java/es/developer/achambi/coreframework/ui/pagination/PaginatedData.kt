package es.developer.achambi.coreframework.ui.pagination

abstract class PaginatedData<D>( val data: ArrayList<D> = ArrayList(),
                        var endPage: Boolean = false,
                        var nextPageIndex: Int = 0 )