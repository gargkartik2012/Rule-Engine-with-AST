package com.example.rule_engine_wit_ast.service;





import com.example.rule_engine_wit_ast.model.Node;
import org.springframework.stereotype.Service;

@Service
public class RuleParser {

    // Method to parse a rule string and create an AST
    public Node createRule(String ruleString) {
        // For simplicity, we're creating a hard-coded AST based on rule1.
        // You can implement more sophisticated parsing logic here.
        Node root = new Node();
        root.setType("operator");
        root.setValue("AND");

        // Create left sub-tree for "((age > 30 AND department = 'Sales') OR (age < 25 AND department = 'Marketing'))"
        Node left = new Node();
        left.setType("operator");
        left.setValue("OR");

        Node leftChild1 = new Node();
        leftChild1.setType("operator");
        leftChild1.setValue("AND");
        Node condition1 = new Node();
        condition1.setType("condition");
        condition1.setValue("age > 30");
        Node condition2 = new Node();
        condition2.setType("condition");
        condition2.setValue("department = 'Sales'");
        leftChild1.setLeft(condition1);
        leftChild1.setRight(condition2);

        Node leftChild2 = new Node();
        leftChild2.setType("operator");
        leftChild2.setValue("AND");
        Node condition3 = new Node();
        condition3.setType("condition");
        condition3.setValue("age < 25");
        Node condition4 = new Node();
        condition4.setType("condition");
        condition4.setValue("department = 'Marketing'");
        leftChild2.setLeft(condition3);
        leftChild2.setRight(condition4);

        left.setLeft(leftChild1);
        left.setRight(leftChild2);

        // Create right sub-tree for "(salary > 50000 OR experience > 5)"
        Node right = new Node();
        right.setType("operator");
        right.setValue("OR");
        Node condition5 = new Node();
        condition5.setType("condition");
        condition5.setValue("salary > 50000");
        Node condition6 = new Node();
        condition6.setType("condition");
        condition6.setValue("experience > 5");
        right.setLeft(condition5);
        right.setRight(condition6);

        // Set the left and right of root
        root.setLeft(left);
        root.setRight(right);

        return root;
    }

    public String combineRules(String rule1String, String rule2String) {
        // Create an operator node for combining the rules
        Node root = new Node();
        root.setType("operator");
        root.setValue("AND"); // You can change this to OR based on your needs

        // Parse both rule strings into AST nodes
        Node rule1AST = createRule(rule1String);
        Node rule2AST = createRule(rule2String);

        // Set the left and right children of the root node
        root.setLeft(rule1AST);
        root.setRight(rule2AST);

        // You may want to return a string representation of the combined rule
        return "Combined Rule: " + rule1String + " AND " + rule2String; // Modify as needed
    }

}
