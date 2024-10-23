package com.example.rule_engine_wit_ast;

import com.example.rule_engine_wit_ast.controller.UIController;
import com.example.rule_engine_wit_ast.model.Node;
import com.example.rule_engine_wit_ast.service.RuleEvaluator;
import com.example.rule_engine_wit_ast.service.RuleParser;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UIControllerTest {


    @InjectMocks
    private UIController uiController;

    @Mock
    private RuleParser ruleParser;

    @Mock
    private RuleEvaluator ruleEvaluator;

    @Mock
    private Model model;

    public UIControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createRuleFromUI_ValidInput_AddsASTToModel() {
        String ruleString = "some rule string";
        Node mockNode = new Node();
        mockNode.setType("operator");
        mockNode.setValue("AND");

        when(ruleParser.createRule(ruleString)).thenReturn(mockNode);

        uiController.createRuleFromUI(ruleString, model);

        verify(model).addAttribute("ast", mockNode);
        verify(model).addAttribute("successMessage", "Rule created successfully!");
    }

    @Test
    void evaluateRule_ValidInput_AddsResultToModel() {
        String ruleString = "some rule string";
        String data = "{\"age\": 30, \"department\": \"Sales\"}";
        String expectedResult = "some evaluation result";


        uiController.evaluateRule(ruleString, data, model);

        verify(model).addAttribute("result", expectedResult);
        verify(model).addAttribute("successMessage", "Rule evaluated successfully!");
    }

    @Test
    void combineRules_ValidInput_AddsCombinedResultToModel() {
        String rule1String = "rule1";
        String rule2String = "rule2";
        String combinedResult = "combined rule";

        when(ruleParser.combineRules(rule1String, rule2String)).thenReturn(combinedResult);

        uiController.combineRules(rule1String, rule2String, model);

        verify(model).addAttribute("combinedResult", combinedResult);
        verify(model).addAttribute("successMessage", "Rules combined successfully!");
    }
}

