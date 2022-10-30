package com.hexad.management.library.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexad.management.library.models.Member;
import com.hexad.management.library.services.MemberService;
import com.hexad.management.library.util.TestData;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberApi.class)
class MemberApiTest extends TestData {
    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private MemberService service;

    @Test
    void getAll() throws Exception {
        List<Member> records = Collections.singletonList(member);
        Mockito.when(service.getAllMembers()).thenReturn(records);

        mvc.perform(MockMvcRequestBuilders
            .get("/api/member/all")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].id", is(member.getId().toString())))
            .andExpect(jsonPath("$[0].password", is(member.getPassword())))
            .andExpect(jsonPath("$[0].email", is(member.getEmail())))
            .andExpect(jsonPath("$[0].name", is(member.getName())))
            .andExpect(jsonPath("$[0].admin", is(member.isAdmin())));
    }

    @Test
    void addMemberExist() throws Exception {
        Mockito.when(service.isExist(member.getEmail())).thenReturn(true);

        mvc.perform(MockMvcRequestBuilders
            .post("/api/member/add")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(member)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success", is(false)));
    }

    @Test
    void addMember() throws Exception {
        Mockito.when(service.isExist(member.getEmail())).thenReturn(false);
        Mockito.when(service.addMember(member)).thenReturn(member);

        mvc.perform(MockMvcRequestBuilders
            .post("/api/member/add")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(member)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success", is(true)))
            .andExpect(jsonPath("$.response.id", is(member.getId().toString())))
            .andExpect(jsonPath("$.response.password", is(member.getPassword())))
            .andExpect(jsonPath("$.response.email", is(member.getEmail())))
            .andExpect(jsonPath("$.response.name", is(member.getName())))
            .andExpect(jsonPath("$.response.admin", is(member.isAdmin())));;
    }

    @Test
    void checkMemberNotExist() throws Exception {
        Mockito.when(service.findMemberBy(
            member.getEmail(),
            member.getPassword())).thenReturn(Optional.empty());

        mvc.perform(MockMvcRequestBuilders
            .post("/api/member/get")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(member)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success", is(false)));;
    }

    @Test
    void checkMember() throws Exception {
        Mockito.when(service.findMemberBy(
                member.getEmail(),
                member.getPassword())).thenReturn(Optional.ofNullable(member));

        mvc.perform(MockMvcRequestBuilders
            .post("/api/member/get")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(member)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success", is(true)))
            .andExpect(jsonPath("$.response", is(member.getId().toString())));;
    }
}