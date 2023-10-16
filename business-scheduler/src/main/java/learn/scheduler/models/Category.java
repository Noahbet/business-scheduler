package learn.scheduler.models;

public enum Category {

    RETAIL(1),
    RESTAURANT(2),
    SERVICE(3);

    private final int categoryId;

    Category(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getCategoryId() {
        return categoryId;
    }

}
