package com.jdabbs.portfolio.repository;

import com.jdabbs.portfolio.model.OrderInstruction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderInstructionRepository extends JpaRepository<OrderInstruction, Long>, CrudRepository<OrderInstruction, Long> {

    OrderInstruction findOrderInstructionById(Long id);

    List<OrderInstruction> findOrderInstructionsByMainInstruction_Id(Long id);




}
