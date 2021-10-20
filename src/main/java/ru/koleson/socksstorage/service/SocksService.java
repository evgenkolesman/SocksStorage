package ru.koleson.socksstorage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.koleson.socksstorage.dto.ReqBody;
import ru.koleson.socksstorage.model.SocksModel;
import ru.koleson.socksstorage.repository.SocksRepository;
import ru.koleson.socksstorage.utils.exceptions.NotFoundException;
import ru.koleson.socksstorage.utils.exceptions.ServerErrorException;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.StreamSupport.stream;
import static ru.koleson.socksstorage.utils.exceptions.Messages.*;

@Service
@RequiredArgsConstructor
public class SocksService {

    private final SocksRepository repository;

    public String saveSocks(SocksModel socks) {
        if (socks.getId() == null) {
            repository.save(socks);
            return SUCCESS.getMessage();

        } else
            throw new ServerErrorException(SERVERERROR);
    }

    public List<SocksModel> findAllSocks(ReqBody req) {
        if (req.getColor().equals("") && req.getCottonPart().equals("")  && req.getOperation().equals("")) {
            return stream(
                    this.repository.findAll().spliterator(), false
            ).collect(Collectors.toCollection(LinkedList::new));

        } else if (req.getOperation().equals("moreThan")) {
            return stream(this.repository.findAllByColorAndCottonPartIsGreaterThan(
                    req.getColor(), Integer.valueOf(req.getCottonPart())).spliterator(), false
            ).collect(Collectors.toCollection(LinkedList::new));

        } else if (req.getOperation().equals("lessThan")) {
            return stream(this.repository.findAllByColorAndCottonPartIsLessThan(
                    req.getColor(), Integer.valueOf(req.getCottonPart())).spliterator(), false
            ).collect(Collectors.toCollection(LinkedList::new));

        } else
            throw new ServerErrorException(SERVERERROR);
    }

    public String deleteSocks(SocksModel socks) {
        if (socks.getId() != null && repository.findSocksById(socks.getId())) {
            repository.delete(socks);
            return SUCCESS.getMessage();
        } else throw new NotFoundException(NOTFOUND);
    }

}
