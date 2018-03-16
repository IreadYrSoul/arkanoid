package level;


public enum BlockType {

    EMPTY(0), BLUE(1), GREEN(2), YELLOW(3), RED(4), VIOLET(5);

    private int n;

    BlockType(int n) {
        this.n = n;
    }

    public static BlockType getByNumeric(int n) {

        switch (n) {
            case 1:
                return BLUE;
            case 2:
                return GREEN;
            case 3:
                return YELLOW;
            case 4:
                return RED;
            case 5:
                return VIOLET;
            default:
                return EMPTY;
        }
    }
}
