package com.hexad.management.library.services;

import com.hexad.management.library.models.Member;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService service = new MemberService();
    UUID testId = UUID.randomUUID();

    public Member newMemberHelper() {

        Member member = service.addMember( Member.builder()
                .password("a")
                .email("frank2@gmail.com")
                .name("Frank2")
                .admin(true)
                .build());

        member.setId(testId);

        return member;
    }

    @Test
    void addMember() {
        Member mem = newMemberHelper();

        assertEquals(mem.getId(), testId);
        assertEquals(mem.getEmail(), "frank2@gmail.com");
        assertEquals(mem.getName(), "Frank2");
        assertEquals(mem.getPassword(), "a");
    }

    @Test
    void getAllMembers() {
        List<Member> allMembers = service.getAllMembers();

        assertNotNull(allMembers);
        assertEquals(allMembers.size(), 1);
    }

    @Test
    void findMemberById() {
        Member mem = newMemberHelper();
        Optional<Member> findMember = service.findMemberById(mem.getId());

        assertTrue(findMember.isPresent());

        Member extractMember = findMember.get();

        assertEquals(mem.getId(), extractMember.getId());
        assertEquals(mem.getEmail(), extractMember.getEmail());
        assertEquals(mem.getName(), extractMember.getName());
        assertEquals(mem.getPassword(), extractMember.getPassword());
    }

    @Test
    void findMemberBy() {
        Optional<Member> mem = service.findMemberBy("frank@gmail.com", "a");

        assertTrue(mem.isPresent());
        Member details = mem.get();
        assertEquals(details.getEmail(), "frank@gmail.com");
        assertEquals(details.getName(), "Frank");
        assertEquals(details.getPassword(), "a");
    }

    @Test
    void isExist() {
        assertTrue(service.isExist("frank@gmail.com"));
        assertFalse(service.isExist("embate@gmail.com"));
    }
}