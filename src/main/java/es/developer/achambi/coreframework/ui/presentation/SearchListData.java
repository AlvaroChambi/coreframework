package es.developer.achambi.coreframework.ui.presentation;

public interface SearchListData {
    default int getViewType() {
        return 0;
    }
    default long getId() {
        return 0;
    }
    default long sortValue() {return 0;}
}
