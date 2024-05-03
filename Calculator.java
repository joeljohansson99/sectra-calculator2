import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Calculator {
    private Map<String, Integer> regs;
    private Map<String, Queue<Expression>> exps;

    public Calculator() {
        this.regs = new HashMap<>();
        this.exps = new HashMap<>();
    }

    public int get_value(String reg) {
        this.eval(reg);
        return this.regs.get(reg);
    }

    public void add_op(String reg, Expression exp) {
        this.exps.putIfAbsent(reg, new LinkedList<>());
        this.exps.get(reg).add(exp);
    }

    private void eval(String reg) {
        this.regs.putIfAbsent(reg, 0);
        
        if (!this.exps.containsKey(reg)) {
            return;
        }

        int value = this.regs.get(reg);
        Queue<Expression> queue = this.exps.get(reg);

        while (!queue.isEmpty()) {
            Expression op = queue.poll();
            // value = op.do_op(value);
        }

        this.regs.put(reg, value);
    }
}
