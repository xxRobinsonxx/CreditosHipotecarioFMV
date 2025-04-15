package com.mivivienda.creditos.repository;

import com.mivivienda.creditos.model.Credito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditoRepository extends JpaRepository<Credito, String> {
}
