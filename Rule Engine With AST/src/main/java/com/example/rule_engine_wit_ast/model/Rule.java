package com.example.rule_engine_wit_ast.model;

public class Rule {
    private Long id;
    private String ruleString;
    private Node rootNode;  // Represents the AST root node

    public Rule(Long id, String ruleString, Node rootNode) {
        this.id = id;
        this.ruleString = ruleString;
        this.rootNode = rootNode;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public String getRuleString() {
        return ruleString;
    }

    public Node getRootNode() {
        return rootNode;
    }
}

