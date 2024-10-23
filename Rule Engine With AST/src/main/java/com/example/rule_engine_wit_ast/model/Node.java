package com.example.rule_engine_wit_ast.model;


public class Node {
    private String type;  // Type can be "operator" or "condition"
    private Node left;    // Left child node
    private Node right;   // Right child node
    private String value; // The value for condition nodes (e.g., "age > 30")

    // Getters and setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isOperator() {
        return "operator".equals(type);
    }

    public boolean isCondition() {
        return "condition".equals(type);
    }
}

