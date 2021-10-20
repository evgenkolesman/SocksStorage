package ru.koleson.socksstorage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.koleson.socksstorage.dto.ReqBody;
import ru.koleson.socksstorage.model.SocksModel;
import ru.koleson.socksstorage.service.SocksService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/socks")
public class SocksController {

    private final SocksService service;

    @GetMapping
    public String findAll(@RequestParam(defaultValue = "") String color,
                          @RequestParam(defaultValue = "") String operation,
                          @RequestParam(defaultValue = "") String cottonPart) {
        ReqBody reqBody = new ReqBody(color,
                operation, cottonPart);
            return service.findAllSocks(reqBody).toString();
    }

    @PostMapping("/income")
    public String makeIncomeOps(@Valid @RequestBody SocksModel socks) {
        return service.saveSocks(socks);
    }

    @PostMapping("/outcome")
    public String makeOutcomeOps(@Valid @RequestBody SocksModel socks) {
        return service.deleteSocks(socks);
    }

}
