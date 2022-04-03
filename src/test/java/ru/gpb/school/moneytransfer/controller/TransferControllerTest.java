package ru.gpb.school.moneytransfer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.gpb.school.moneytransfer.model.Transfer;
import ru.gpb.school.moneytransfer.service.ReplenishmentService;
import ru.gpb.school.moneytransfer.service.TransferService;
import ru.gpb.school.moneytransfer.service.WithdrawalService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static ru.gpb.school.moneytransfer.TestUtils.getTestTransfers;

@WebMvcTest(value = TransferController.class)
class TransferControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    TransferService transferService;
    @MockBean
    WithdrawalService withdrawalService;
    @MockBean
    ReplenishmentService replenishmentService;

    @Test
    void getAll() throws Exception {
        List<Transfer> expected = getTestTransfers();

        Mockito.when(transferService.findAll()).thenReturn(expected);

        MvcResult mvcResult = mockMvc.perform(get("/")).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        ObjectReader objectReader = mapper.readerFor(Transfer.class);
        List<Transfer> objects = objectReader.<Transfer>readValues(response.getContentAsString()).readAll();
        assertThat(objects).usingRecursiveFieldByFieldElementComparator().isEqualTo(expected);
    }

    @Test
    void getTransferByRecipient() {
    }

    @Test
    void getTransfersBySender() {
    }

    @Test
    void getTransfersByDate() {
    }

    @Test
    void saving() {
    }

    @Test
    void testSaving() {
    }

    @Test
    void testSaving1() {
    }
}