package com.jsp.repository;

import com.jsp.ruleengine.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleRepository extends JpaRepository<Node, Long> {
 // No additional methods are needed for basic CRUD operations, as JpaRepository provides them.
 // You can add custom queries here if needed in the future.
}
