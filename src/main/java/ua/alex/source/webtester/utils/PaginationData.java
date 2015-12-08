package ua.alex.source.webtester.utils;


public class PaginationData {
    private final int radius;
    private int countPage;
    private int page;

    public PaginationData(long totalCount, int countRows, int page) {
        this.countPage = (int) Math.ceil(totalCount * 1.0 / countRows);
        this.radius = 5;
        this.page = page;
    }

    public int getCountPage() {
        return countPage;
    }

    public void setCountPage(int countPage) {
        this.countPage = countPage;
    }

    public int getStartIndex() {
        return page - radius > 0 ? page - radius : 1;
    }

    public int getFinishIndex() {
        return page + radius > getCountPage() ? getCountPage() : page + radius;
    }

    public int getPage() {
        if (page > countPage) page = countPage;
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
