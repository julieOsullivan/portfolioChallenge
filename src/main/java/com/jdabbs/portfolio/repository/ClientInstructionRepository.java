package com.jdabbs.portfolio.repository;

import com.jdabbs.portfolio.model.ClientInstruction;
import com.jdabbs.portfolio.model.OrderInstruction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientInstructionRepository extends JpaRepository<ClientInstruction, Long> {

   }
