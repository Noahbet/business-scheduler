package learn.scheduler.models;

public enum Category {

    HEALTH(1),
    HAIR(2),
    TATTOO(3),
    PET(4),
    CLEANING(5),
    FOOD(6);

    private final int categoryId;

    Category(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getCategoryId() {
        return categoryId;
    }

}
