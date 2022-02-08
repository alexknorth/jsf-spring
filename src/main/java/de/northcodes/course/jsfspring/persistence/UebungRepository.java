package de.northcodes.course.jsfspring.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import de.northcodes.course.jsfspring.model.Uebung;
import org.springframework.data.repository.query.Param;

public interface UebungRepository extends CrudRepository<Uebung, Long> {

    @Query("select u from Uebung u where u.name like :name")
    Uebung findByName(@Param("name") String name);

}