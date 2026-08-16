package hello.helloSpring.controller;

import hello.helloSpring.domain.Member;
import hello.helloSpring.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class MemberControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberService memberService;

    @Test
    void createsAndListsMember() throws Exception {
        mockMvc.perform(post("/members/new").param("name", "spring"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        assertThat(memberService.findMembers())
                .extracting(Member::getName)
                .containsExactly("spring");

        mockMvc.perform(get("/members"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("spring")));
    }

    @Test
    void returnsNameFromHelloApi() throws Exception {
        mockMvc.perform(get("/hello-api").param("name", "Spring"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Spring"));
    }
}
