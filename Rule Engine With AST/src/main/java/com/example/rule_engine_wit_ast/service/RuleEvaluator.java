package com.example.rule_engine_wit_ast.service;


import com.example.rule_engine_wit_ast.model.Node;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class RuleEvaluator {

    // Method to evaluate the rule (AST) against the provided user data
    public boolean evaluateRule(Node node, Map<String, Object> data) {
        if (node == null) {
            throw new IllegalArgumentException("Node cannot be null");
        }

        if (node.isOperator()) {
            if (node.getValue().equals("AND")) {
                return evaluateRule(node.getLeft(), data) && evaluateRule(node.getRight(), data);
            } else if (node.getValue().equals("OR")) {
                return evaluateRule(node.getLeft(), data) || evaluateRule(node.getRight(), data);
            }
        } else if (node.isCondition()) {
            return checkCondition(node.getValue(), data);
        }
        return false;
    }

    // Helper method to evaluate a condition (e.g., "age > 30")
    private boolean checkCondition(String condition, Map<String, Object> data) {
        String[] parts = condition.split(" ");
        String attribute = parts[0];
        String operator = parts[1];
        String value = parts[2].replace("'", ""); // Remove quotes for string comparison

        Object dataValue = data.get(attribute);
        if (dataValue == null) {
            return false; // Attribute not found in data
        }

        // Handle different operators for conditions
        switch (operator) {
            case ">":
                return Integer.parseInt(dataValue.toString()) > Integer.parseInt(value);
            case "<":
                return Integer.parseInt(dataValue.toString()) < Integer.parseInt(value);
            case "=":
                return dataValue.toString().equals(value);
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

}
