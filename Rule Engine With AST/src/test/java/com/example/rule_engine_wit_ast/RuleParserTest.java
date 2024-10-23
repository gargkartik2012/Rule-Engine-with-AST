package com.example.rule_engine_wit_ast;

import com.example.rule_engine_wit_ast.model.Node;
import com.example.rule_engine_wit_ast.service.RuleParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RuleParserTest {
    private final RuleParser ruleParser = new RuleParser();

    @Test
    void createRule_ValidRule_ReturnsAST() {
        String ruleString = "((age > 30 AND department = 'Sales') OR (age < 25 AND department = 'Marketing'))";
        Node ast = ruleParser.createRule(ruleString);

        assertNotNull(ast);
        assertEquals("operator", ast.getType());
        assertEquals("AND", ast.getValue());
    }

    @Test
    void createRule_InvalidRule_ThrowsException() {
        String invalidRule = "invalid_rule";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ruleParser.createRule(invalidRule);
        });

        assertTrue(exception.getMessage().contains("Error parsing rule:"));
    }
}

