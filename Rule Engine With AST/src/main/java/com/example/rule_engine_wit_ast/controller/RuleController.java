package com.example.rule_engine_wit_ast.controller;

import com.example.rule_engine_wit_ast.model.Node;
import com.example.rule_engine_wit_ast.service.RuleService;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.Map;

public class RuleController {

    private final RuleService ruleService;

    public RuleController() {
        this.ruleService = new RuleService();
    }

    @PostMapping("/ui/rules/create")
    public Node createRule(String rule) {
        return ruleService.createRule(rule);
    }

    // Method to evaluate the rule against the provided data
    @PostMapping("/ui/rules/evaluate")
    public boolean evaluateRule(Node node, Map<String, Object> data) {
        return ruleService.evaluateRule(node, data);
    }

    // Method to combine two rules (AST nodes) into one
    @PostMapping("/ui/rules/combine")
    public Node combineRules(Node rule1, Node rule2) {
        return ruleService.combineRules(rule1, rule2);
    }

    public static void main(String[] args) {
        // Initialize the controller
        RuleController controller = new RuleController();

        // Create a rule using the RuleController
        Node ruleNode = controller.createRule("age > 30 AND department = 'Sales'");

        // Sample data to test the rule evaluation
        Map<String, Object> data = Map.of(
                "age", 35,
                "department", "Sales"
        );

        // Evaluate the rule against the provided data
        boolean result = controller.evaluateRule(ruleNode, data);

        // Output the evaluation result
        System.out.println("Evaluation Result: " + result);

        // You can also test combining rules, e.g., combining two different rule nodes
        Node ruleNode2 = controller.createRule("salary > 50000 OR experience > 5");
        Node combinedRuleNode = controller.combineRules(ruleNode, ruleNode2);

        // Re-evaluate combined rule with data
        boolean combinedResult = controller.evaluateRule(combinedRuleNode, data);
        System.out.println("Combined Rule Evaluation Result: " + combinedResult);
    }
}
