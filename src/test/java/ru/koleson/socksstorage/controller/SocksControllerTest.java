package ru.koleson.socksstorage.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.koleson.socksstorage.SocksStorageApplication;
import ru.koleson.socksstorage.dto.ReqBody;
import ru.koleson.socksstorage.model.SocksModel;
import ru.koleson.socksstorage.repository.SocksRepository;
import ru.koleson.socksstorage.service.SocksService;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.koleson.socksstorage.utils.exceptions.Messages.*;

@SpringBootTest(classes = SocksStorageApplication.class)
@AutoConfigureMockMvc
class SocksControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    SocksService service;

    @MockBean
    SocksRepository repository;

    @Test
    void findAllTestAllNulls() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        SocksModel socks1 = new SocksModel(1L,"White", 1, 1);
        SocksModel socks2 = new  SocksModel(2L, "Black", 90, 12);
        when(service.findAllSocks(new ReqBody())).thenReturn(List.of(socks1, socks2));
        String req = mapper.writer().writeValueAsString(List.of(socks1, socks2));
        mvc.perform(get("/api/socks").contentType("application/json").content(req))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void findAllTest() throws Exception {
        SocksModel socks2 = new  SocksModel(2L, "Black", 90, 12);
        when(service.findAllSocks(new ReqBody("Black", "moreThen", "10"))).thenReturn(List.of(socks2));
        mvc.perform(get("/api/socks?color=Black&operation=moreThan&cottonPart=10").contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void makeIncomeOpsTestWrongData() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        SocksModel socks1 = new SocksModel(1L,"White", 1, 1);
        when(service.saveSocks(socks1)).thenReturn(SERVERERROR.getMessage());
        String req = mapper.writer().writeValueAsString(socks1);
        mvc.perform(post("/api/socks/income").contentType(MediaType.APPLICATION_JSON)
                        .content(req))
                .andDo(print())
                .andExpect(content().string(containsString(SERVERERROR.getMessage())));

    }

    @Test
    void makeIncomeOpsTestCorrectData() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        SocksModel socks1 = SocksModel.of("White", 1, 1);
        when(service.saveSocks(socks1)).thenReturn(SUCCESS.getMessage());
        String req = mapper.writer().writeValueAsString(socks1);
        mvc.perform(post("/api/socks/income").contentType(MediaType.APPLICATION_JSON)
                        .content(req))
                .andDo(print())
                .andExpect(content().string(containsString(SUCCESS.getMessage())));

    }

    @Test
    void makeOutcomeOpsTestCorrectData() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        SocksModel socks1 = SocksModel.of("White", 1, 1);
        when(service.deleteSocks(socks1)).thenReturn(SUCCESS.getMessage());
        String req = mapper.writer().writeValueAsString(socks1);
        mvc.perform(post("/api/socks/outcome").contentType(MediaType.APPLICATION_JSON)
                        .content(req))
                .andDo(print())
                .andExpect(content().string(containsString(SUCCESS.getMessage())));
    }

    @Test
    void makeOutcomeOpsTestWrongData() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        SocksModel socks1 = new SocksModel(10L,"White", 1, 1);
        when(service.deleteSocks(socks1)).thenReturn(NOTFOUND.getMessage());
        String req = mapper.writer().writeValueAsString(socks1);
        mvc.perform(post("/api/socks/outcome").contentType(MediaType.APPLICATION_JSON)
                        .content(req))
                .andDo(print())
                .andExpect(content().string(containsString(NOTFOUND.getMessage())));
    }
}