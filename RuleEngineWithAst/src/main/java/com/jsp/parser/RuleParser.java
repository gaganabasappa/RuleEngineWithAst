package com.jsp.parser;
import com.jsp.ruleengine.*;
public class RuleParser {
	    public Node createRule(String ruleString) {
	        // Basic parsing logic to convert a string rule to an AST
	        // For simplicity, assume well-formed input
	        ruleString = ruleString.trim();
	        if (ruleString.startsWith("(")) {
	            // Find the matching closing parenthesis for the first opening parenthesis
	            int closingIndex = findClosingParenthesis(ruleString);
	            if (closingIndex == ruleString.length() - 1) {
	                return createRule(ruleString.substring(1, closingIndex));
	            }
	        }

	        // Split the rule into left and right parts based on the first occurrence of AND/OR
	        int andIndex = ruleString.indexOf(" AND ");
	        int orIndex = ruleString.indexOf(" OR ");

	        if (andIndex > -1 && (orIndex == -1 || andIndex < orIndex)) {
	            Node left = createRule(ruleString.substring(0, andIndex));
	            Node right = createRule(ruleString.substring(andIndex + 5));
	            return new Node("operator", left, right, "AND");
	        } else if (orIndex > -1) {
	            Node left = createRule(ruleString.substring(0, orIndex));
	            Node right = createRule(ruleString.substring(orIndex + 4));
	            return new Node("operator", left, right, "OR");
	        } else {
	            // Operand case (no AND/OR)
	            return new Node("operand", null, null, ruleString);
	        }
	    }

	    private int findClosingParenthesis(String str) {
	        int openCount = 0;
	        for (int i = 0; i < str.length(); i++) {
	            if (str.charAt(i) == '(') openCount++;
	            else if (str.charAt(i) == ')') openCount--;
	            if (openCount == 0) return i;
	        }
	        return -1; // No matching closing parenthesis found
	    }
}
