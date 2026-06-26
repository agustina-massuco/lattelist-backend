package com.example.LatteListBack.Repositorys;

import com.example.LatteListBack.Models.ListaDeCafes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListaDeCafesRepository extends JpaRepository<ListaDeCafes, Long> {

    @Query("SELECT l FROM ListaDeCafes l JOIN FETCH l.usuario WHERE l.usuario.email = :email")
    List<ListaDeCafes> findByUsuario_Email(String email);
    List<ListaDeCafes> findByPublicaTrue();
}
