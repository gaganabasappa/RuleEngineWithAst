package com.jsp.controller;



import com.jsp.controller.*;
import com.jsp.parser.*;
import com.jsp.repository.RuleRepository;
import com.jsp.ruleengine.*;
import com.jsp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/rules")
public class RuleController {
 @Autowired
 private RuleParser ruleParser;

 @Autowired
 private RuleRepository ruleRepository;

 @Autowired
 private RuleEvaluator ruleEvaluator;

 @PostMapping("/create")
 public Node createRule(@RequestBody String ruleString) {
     Node rule = ruleParser.createRule(ruleString);
     return ruleRepository.save(rule);
 }

 @PostMapping("/evaluate/{ruleId}")
 public boolean evaluateRule(@PathVariable Long ruleId, @RequestBody Map<String, Object> data) {
     Node rule = ruleRepository.findById(ruleId).orElseThrow();
     return ruleEvaluator.evaluateRule(rule, data);
 }
}

