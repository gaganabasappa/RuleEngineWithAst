package com.jsp.ruleengine;

public class Node {
	    private String type;     // "operator" or "operand"
	    private Node left;       // Left child for operator nodes
	    private Node right;      // Right child for operator nodes
	    private String value;    // Condition value for operand nodes or operator type (AND/OR)

	    public Node(String type, Node left, Node right, String value) {
	        this.type = type;
	        this.left = left;
	        this.right = right;
	        this.value = value;
	    }

	    // Getters and Setters
	    public String getType() { return type; }
	    public void setType(String type) { this.type = type; }
	    public Node getLeft() { return left; }
	    public void setLeft(Node left) { this.left = left; }
	    public Node getRight() { return right; }
	    public void setRight(Node right) { this.right = right; }
	    public String getValue() { return value; }
	    public void setValue(String value) { this.value = value; }
	}


