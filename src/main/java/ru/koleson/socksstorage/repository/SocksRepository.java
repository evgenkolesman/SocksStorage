package ru.koleson.socksstorage.repository;

import org.springframework.data.repository.CrudRepository;
import ru.koleson.socksstorage.model.SocksModel;

import java.util.List;

public interface SocksRepository extends CrudRepository<SocksModel, Long> {

    boolean findSocksById(Long id);

    List<SocksModel> findAllByColorAndCottonPartIsLessThan(String color, Integer cottonPart);

    List<SocksModel> findAllByColorAndCottonPartIsGreaterThan(String color, Integer cottonPart);

}
