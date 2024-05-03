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

	// Error checking for register name
	// Even if a number is alphanumeric, it would be confusing if it could be a register too
	public boolean valid_reg(String reg) {
		return reg.matches("[A-Za-z0-9]+") && !valid_number(reg);
	}

	// Error checking for number
	public boolean valid_number(String num) {
		try {
			Integer.parseInt(num);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// Evaluate and return value
    public int get_value(String reg) {
        this.eval(reg);
        return this.regs.get(reg);
    }

	// Add expression for later evaluation
    public void add_exp(String reg, Expression exp) {
        this.exps.putIfAbsent(reg, new LinkedList<>());
        this.exps.get(reg).add(exp);
    }

	// Evaluate value of register
    private void eval(String reg) {
        this.regs.putIfAbsent(reg, 0);
        
        if (!this.exps.containsKey(reg)) {
			// Nothing to evaluate
            return;
        }

        int reg_val = this.regs.get(reg);

		// Get pending expressions
        Queue<Expression> queue = this.exps.get(reg);

        while (!queue.isEmpty()) {
            Expression exp = queue.poll();
			// Get operand
			Operand op = exp.get_op();

			// Get value
            int exp_val;
			if (exp.is_number()) {
				exp_val = Integer.parseInt(exp.get_value());
			} else {
				exp_val = this.get_value(exp.get_value());
			}

			// Do operation
			reg_val = op.do_op(reg_val, exp_val);
        }

		// Add new value
        this.regs.put(reg, reg_val);
    }
}
