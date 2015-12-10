package py.com.ventu.util;

/**
 * @author progress
 */
public class FiltersObject {

    private int first;
    private int pageSize;
    
    private String sortField;
    private String filters;

    public FiltersObject() {
    }

    public FiltersObject(int first, int pageSize, String sortField, String filters) {
        this.first = first;
        this.pageSize = pageSize;

        if (sortField == null) {
            this.sortField = "id";
        } else {
            this.sortField = sortField;
        }

        this.filters = filters;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getFilters() {
        return this.filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }
}
