package common.baselibrary.irecyclerview.expandable.model;

import java.util.List;

/**
 * Interface for implementing required methods in a parent list item.
 * Created by chasen on 2018/4/16.
 */
public interface ExpandableListItem {

    /**
     * Getter for the list of this parent list item's child list items.
     *
     * @return A {@link List} of the children of this {@link ExpandableListItem}
     */
    List<?> getChildItemList();

    /**
     * @return true if expanded, false if not
     */
    boolean isExpanded();

    /**
     * set expand state
     * @param isExpanded
     */
    void setExpanded(boolean isExpanded);
}