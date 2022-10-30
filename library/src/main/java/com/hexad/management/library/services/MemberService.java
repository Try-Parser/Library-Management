package com.hexad.management.library.services;

import com.hexad.management.library.models.Member;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Scope("singleton")
public class MemberService {
    private final List<Member> member = new ArrayList<>();

    public MemberService() {
        member.add(Member.builder()
            .id(UUID.randomUUID())
            .password("a")
            .email("frank@gmail.com")
            .name("Frank")
            .admin(true)
            .build());
    }

    /***
     * Add new member
     * @param person Member
     * @return Member
     */
    public Member addMember(Member person) {
        person.setId(UUID.randomUUID());
        person.setCreatedAt(new Date());
        member.add(person);
        return person;
    }

    /***
     * returns all the members
     * @return List<Member>
     */
    public List<Member> getAllMembers() {
        return member;
    }

    /***
     * Find member by id
     * @param id UUID
     * @return Optional<Member>
     */
    public Optional<Member> findMemberById(UUID id) {
        return member.parallelStream()
            .filter(details -> details.getId().equals(id))
            .findFirst();
    }

    /***
     * Find Member by email and password
     * @param email String
     * @param password String
     * @return Optional<Member>
     */
    public Optional<Member> findMemberBy(String email, String password) {
        return member.parallelStream()
            .filter(details ->
            details.getEmail().equals(email) &&
            details.getPassword().equals(password)).findFirst();
    }

    /***
     * Check if the user exist
     * @param email String
     * @return Boolean (true / false)
     */
    public boolean isExist(String email) {
        return member.parallelStream()
            .anyMatch(detail -> detail.getEmail().equals(email));
    }

}
