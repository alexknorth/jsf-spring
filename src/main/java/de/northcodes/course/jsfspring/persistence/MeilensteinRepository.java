
package de.northcodes.course.jsfspring.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import de.northcodes.course.jsfspring.model.Meilenstein;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MeilensteinRepository extends CrudRepository<Meilenstein, Long> {

    @Query("select m from Meilenstein m where m.uebung.id = :uebungId")
    List<Meilenstein> findAllByUebungId(@Param("uebungId") long uebungId);

}