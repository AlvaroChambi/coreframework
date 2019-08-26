package es.developer.achambi.coreframework.ui.pagination

abstract class PaginatedData<D>( val data: ArrayList<D> = ArrayList(),
                        var endPage: Boolean = false,
                        var count: Int = 0,
                        var nextPageIndex: Int = 0 )