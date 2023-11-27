package com.example.BancTransfer.repository;

import com.example.BancTransfer.entity.ContBancar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContBancarRepository extends JpaRepository<ContBancar, Integer> {

    ContBancar findByIdPerson(Integer idPerson);

    ContBancar findByIdPersonAndCurrency(Integer idPerson, String currency);

    ContBancar deleteByIdCont(Integer idCont);
}
