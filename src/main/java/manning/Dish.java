package manning;

public class Dish {

    private final String name;
    private final int calories;
    private final Type type;

    public Dish(String name, boolean b, int i, Type p3) {
        this.name = name;
        this.calories = i;
        this.type = p3;
    }

    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        FISH, MEAT, OTHER
    }
}
