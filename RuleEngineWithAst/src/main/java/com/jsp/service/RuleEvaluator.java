// RuleEvaluator.java
package com.jsp.service;

import com.jsp.ruleengine.*;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RuleEvaluator {
    public boolean evaluateRule(Node node, Map<String, Object> data) {
        if (node == null) return false;

        if ("operand".equals(node.getType())) {
            return evaluateCondition(node.getValue(), data);
        }

        boolean leftResult = evaluateRule(node.getLeft(), data);
        boolean rightResult = evaluateRule(node.getRight(), data);

        if ("AND".equals(node.getValue())) {
            return leftResult && rightResult;
        } else if ("OR".equals(node.getValue())) {
            return leftResult || rightResult;
        }

        return false;
    }

    private boolean evaluateCondition(String condition, Map<String, Object> data) {
        String[] parts = condition.split(" ");
        if (parts.length != 3) return false;

        String attribute = parts[0];
        String operator = parts[1];
        String value = parts[2];

        Object attributeValue = data.get(attribute);
        if (attributeValue == null) return false;

        switch (operator) {
            case ">":
                return ((Comparable) attributeValue).compareTo(Integer.parseInt(value)) > 0;
            case "<":
                return ((Comparable) attributeValue).compareTo(Integer.parseInt(value)) < 0;
            case "=":
                return attributeValue.toString().equals(value);
            default:
                return false;
        }
    }
}

