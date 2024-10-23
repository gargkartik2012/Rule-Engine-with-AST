package com.example.rule_engine_wit_ast.controller;

import com.example.rule_engine_wit_ast.model.Node;
import com.example.rule_engine_wit_ast.service.RuleEvaluator;
import com.example.rule_engine_wit_ast.service.RuleParser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class UIController {

    @Autowired
    private RuleParser ruleParser;

    @Autowired
    private RuleEvaluator ruleEvaluator;

    @GetMapping("/ui")
    public String getUI() {
        return "rule-engine"; // Refers to rule-engine.html in /templates
    }

    @GetMapping("/rules")
    public String showRulePage() {
        return "rules";  // This should match your Thymeleaf template name
    }

    @PostMapping("/ui/rules/create")
    public String createRuleFromUI(@RequestParam(defaultValue = "") String ruleString, Model model) {
        if (ruleString.isEmpty()) {
            model.addAttribute("errorMessage", "Rule string is required.");
            return "rule-engine";  // Return the same page with an error message
        }
        Node ast = ruleParser.createRule(ruleString);
        model.addAttribute("ast", ast);  // Display AST in Thymeleaf
        model.addAttribute("successMessage", "Rule created successfully!"); // Success message
        return "rule-engine";  // Renders the same page with the AST result
    }

    @PostMapping("/ui/rules/evaluate")
    public String evaluateRule(@RequestParam String ruleString, @RequestParam String data, Model model) {
        // Parse the rule string into an AST Node
        Node ast = ruleParser.createRule(ruleString);

        // Convert the input JSON data into a Map
        Map<String, Object> dataMap = new Gson().fromJson(data, new TypeToken<Map<String, Object>>() {}.getType());

        // Evaluate the rule
        boolean result = ruleEvaluator.evaluateRule(ast, dataMap);

        // Add the evaluation result to the model
        model.addAttribute("result", result);
        model.addAttribute("successMessage", "Rule evaluated successfully!");

        return "rule-engine"; // Return the name of your view template
    }
    


    @PostMapping("/ui/rules/combine")
    public String combineRules(@RequestParam String rule1String, @RequestParam String rule2String, Model model) {
        // Assume you have a method in RuleParser to combine the rules
        String combinedRule = ruleParser.combineRules(rule1String, rule2String);
        model.addAttribute("combinedResult", combinedRule);  // Display combined rule in Thymeleaf
        model.addAttribute("successMessage", "Rules combined successfully!"); // Success message
        return "rule-engine";  // Renders the same page with the combined result
    }
}
