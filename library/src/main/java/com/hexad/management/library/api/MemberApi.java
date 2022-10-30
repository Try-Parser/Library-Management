package com.hexad.management.library.api;

import com.hexad.management.library.models.Member;
import com.hexad.management.library.services.MemberService;
import com.hexad.management.library.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/***
 * Member API
 */
@RestController
@RequestMapping("/api/member/")
public class MemberApi {

    @Autowired
    MemberService service;

    /***
     * Get all members info
     * @return List<Member>
     */
    @GetMapping("all")
    @ResponseBody
    public List<Member> getAll() {
        return service.getAllMembers() ;
    }

    /***
     * Creating new Member
     * @param member Member
     * @return Response<?>
     */
    @PostMapping("add")
    @ResponseBody
    public Response<?> addMember(@RequestBody Member member) {
        if(service.isExist(member.getEmail())) {
            return Response.builder()
                .response("Email exist!")
                .success(false)
                .build();
        }

        return Response.builder()
            .response(service.addMember(member))
            .success(true)
            .build();
    }

    /***
     * Check if member exist
     * @param member Member
     * @return Response<?>
     */
    @PostMapping("get")
    @ResponseBody
    public Response<?> checkMember(@RequestBody Member member) {
        Optional<Member> credentials = service.findMemberBy(member.getEmail(), member.getPassword());

        if(credentials.isPresent()) {
            return Response.builder()
                    .response(credentials.get().getId())
                    .success(true)
                    .build();
        }

        return Response.builder()
            .success(false)
            .build();
    }

}
