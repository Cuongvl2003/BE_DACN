package com.backend.book.Model.DTO;

public class PageUpdateRequest {
    private String id;        // Book ID
    private String page;    // Page content or identifier
    private Long pageNumber; // Page number

    // Getters and setters (or use Lombok @Data for brevity)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Long getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Long pageNumber) {
        this.pageNumber = pageNumber;
    }
}
