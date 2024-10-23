package com.example.rule_engine_wit_ast;

import com.example.rule_engine_wit_ast.model.Node;
import com.example.rule_engine_wit_ast.service.RuleEvaluator;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RuleEvaluatorTest {
    private final RuleEvaluator ruleEvaluator = new RuleEvaluator();

    @Test
    void evaluateRule_ValidAST_ReturnsTrue() {
        Node ast = new Node();
        ast.setType("operator");
        ast.setValue("AND");

        Node condition1 = new Node();
        condition1.setType("condition");
        condition1.setValue("age > 30");
        Node condition2 = new Node();
        condition2.setType("condition");
        condition2.setValue("department = 'Sales'");

        ast.setLeft(condition1);
        ast.setRight(condition2);

        Map<String, Object> data = new HashMap<>();
        data.put("age", 31);
        data.put("department", "Sales");

        boolean result = ruleEvaluator.evaluateRule(ast, data);
        assertTrue(result);
    }

    @Test
    void evaluateRule_InvalidAST_ReturnsFalse() {
        Node ast = new Node();
        ast.setType("operator");
        ast.setValue("OR");

        Node condition1 = new Node();
        condition1.setType("condition");
        condition1.setValue("age < 30");
        Node condition2 = new Node();
        condition2.setType("condition");
        condition2.setValue("department = 'Sales'");

        ast.setLeft(condition1);
        ast.setRight(condition2);

        Map<String, Object> data = new HashMap<>();
        data.put("age", 31);
        data.put("department", "Sales");

        boolean result = ruleEvaluator.evaluateRule(ast, data);
        assertFalse(result);
    }

}
