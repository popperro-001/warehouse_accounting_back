package com.warehouse_accounting.integration_tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.warehouse_accounting.controllers.rest.LegalDetailRestController;
import com.warehouse_accounting.models.dto.LegalDetailDto;
import com.warehouse_accounting.models.dto.TypeOfContractorDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@Sql(value = {"classpath:init-type_of_contractor-table.sql", "classpath:init-legal_detail-table.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class LegalDetailRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private LegalDetailRestController legalDetailRestController;
    @Autowired
    private ObjectMapper mapper;

    private static final List<LegalDetailDto> legalDetailDtoList = new ArrayList<>();


    @BeforeAll
    static void initLegalDetail() {

        LegalDetailDto legalDetailDto_1 = LegalDetailDto.builder()
                .id(1L)
                .fullName("????????????????????")
                .address("1-?? ?????????????????? ????-??, 4, ??????. 19, ????????????, 109428")
                .commentToAddress("-")
                .inn("7731537604")
                .okpo("93318947")
                .ogrn("1067746195330")
                .kpp("111111111")
                .typeOfContractorId(1L)
                .typeOfContractorName("?????????????????????? ????????")
                .build();

        LegalDetailDto legalDetailDto_2 = LegalDetailDto.builder()
                .id(2L)
                .fullName("??????????????")
                .address("???????????? ??????., ??. ????????, ????. ????????????, ??. 38 ??????. 16, ???????? 4")
                .commentToAddress("-")
                .inn("7631588604")
                .kpp("111111111")
                .okpo("04152913")
                .ogrn("1165543081879")
                .typeOfContractorId(1L)
                .typeOfContractorName("?????????????????????? ????????")
                .build();

        LegalDetailDto legalDetailDto_3 = LegalDetailDto.builder()
                .id(3L)
                .fullName("????????????")
                .address("?????????????????????????? ????., ??. ?????????????? ????????, ????. ????????????, ??. 37")
                .commentToAddress("????????????????")
                .inn("2305023045")
                .kpp("111111111")
                .okpo("93814283")
                .ogrn("1062305001440")
                .typeOfContractorId(2L)
                .typeOfContractorName("???????????????????????????? ??????????????????????????????")
                .build();

        LegalDetailDto legalDetailDto_4 = LegalDetailDto.builder()
                .id(4L)
                .fullName("????????????")
                .address("??. ????????????, ????. 1-?? ??????????????????????????, ??. 2 ??????. 1 ???????? 3, ??????. 8")
                .commentToAddress("")
                .inn("7714921994")
                .kpp("111111111")
                .okpo("22709223")
                .ogrn("5137746124368")
                .typeOfContractorId(2L)
                .typeOfContractorName("???????????????????????????? ??????????????????????????????")
                .build();

        legalDetailDtoList.addAll(Arrays.asList(legalDetailDto_1, legalDetailDto_2, legalDetailDto_3, legalDetailDto_4));
    }

    @Test
    void testExistence() {
        assertThat(legalDetailRestController).isNotNull();
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/legal_details"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(legalDetailDtoList)));
    }

    @Test
    void testGetById() throws Exception {
        mockMvc.perform(get("/api/legal_details/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(legalDetailDtoList.get(1))));
    }

    @Test
    void testCreate() throws Exception {

        LegalDetailDto legalDetailDto_5 = LegalDetailDto.builder()
                .id(5L)
                .fullName("??????????")
                .address("???????????????????? ??????., ??. ??????????????, ?????????????? ??????. ??????????????, ?????????? ??????????????????????, ??. 6 ??????. 1 ???????? 59")
                .commentToAddress("")
                .inn("7743013902")
                .kpp("111111111")
                .okpo("")
                .ogrn("")
                .typeOfContractorId(3L)
                .typeOfContractorName("???????????????????? ????????")
                .build();

        legalDetailDtoList.add(legalDetailDto_5);

        mockMvc.perform(post("/api/legal_details")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(legalDetailDto_5)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/legal_details/5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(legalDetailDto_5)));

        mockMvc.perform(get("/api/legal_details"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(legalDetailDtoList)));

        legalDetailDtoList.remove(legalDetailDto_5);
    }

    @Test
    void testUpdate() throws Exception {
        LegalDetailDto legalDetailDto_1 = LegalDetailDto.builder()
                .id(1L)
                .fullName("????????????????????")
                .address("1-?? ?????????????????? ????-??, 4, ??????. 19, ????????????, 109428")
                .commentToAddress("-")
                .inn("7731537604")
                .kpp("111111111")
                .okpo("93318947")
                .ogrn("1067746195330")
                .typeOfContractorId(1L)
                .typeOfContractorName("?????????????????????? ????????")
                .build();

        mockMvc.perform(put("/api/legal_details")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(legalDetailDto_1)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/legal_details/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(legalDetailDto_1)));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/api/legal_details/4"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/legal_details/4"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void testFilter() throws Exception {
        List<LegalDetailDto> collectBetween = legalDetailDtoList.stream()
                .filter(dto -> dto.getId().equals(2L))
                .collect(Collectors.toList());
//
//        List<LegalDetailDto> collectBetweenAndType = legalDetailDtoList.stream()
//                .filter(dto -> dto.getId().equals(2L))
//                .collect(Collectors.toList());

        mockMvc.perform(get("/api/legal_details/filter?fullName=??????????????"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(collectBetween)));

//        mockMvc.perform(get("/api/legal_details/filter?dateOfTheCertificateBetween=2021-01-01;2021-03-18" +
//                "&typeOfContractorName=?????????????????????? ????????"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().json(mapper.writeValueAsString(collectBetweenAndType)));
//
//        mockMvc.perform(get("/api/legal_details/filter?dateOfTheCertificateBetween=2021-01-01;2021-03-18" +
//                "&typeOfContractorName=?????????????????????? ????????" +
//                "&inn=1234567890"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().json("[]"));
    }
}
