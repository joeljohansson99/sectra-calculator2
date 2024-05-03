
abstract class Operand {

    public static Operand parse(String op) {
		// Match operation
        switch (op) {
            case "add":
                return new Add();
            case "subtract":
                return new Sub();
            case "multiply":
                return new Mul();
            default:
                return null;
        }
    }

    abstract int do_op(int x, int y);
}

// Operations
class Add extends Operand {
    @Override
    int do_op(int x, int y) {
        return x + y;
    }
}

class Sub extends Operand {
    @Override
    int do_op(int x, int y) {
        return x - y;
    }
}

class Mul extends Operand {
    @Override
    int do_op(int x, int y) {
        return x * y;
    }
}