package com.javarush.jira.bugtracking.tag;

import com.javarush.jira.AbstractControllerTest;
import com.javarush.jira.bugtracking.task.Task;
import com.javarush.jira.bugtracking.task.TaskRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TagControllerTest extends AbstractControllerTest {
/*    @Value("${DATABASE.USERNAME}")
    private String DATABASE;
    //@Value("${database.url}")
    //private String DATABASE2;
    @Test
    void valueTestKillThis(){
        System.out.println("DataBase_Username="+DATABASE);
    }
//      -Dspring.profiles.active=dev
*/

    @Autowired
    private  TaskRepository taskRepository;
    @BeforeEach
    @Transactional
     void firstCommit(){
        Task task = taskRepository.getExisted(1L);
        Set<String> tags=new HashSet<>();
        tags.add("tag2");
        task.setTags(tags);
        taskRepository.save(task);
        taskRepository.flush();
    }
    @Test
    //@WithUserDetails("someUser@app.com")
//    @Transactional
    @WithMockUser(username = "user1", password = "pwd", roles = "ADMIN")
    void shouldReturnGottenSetTagTo_getTags() throws Exception {
//        Task task = taskRepository.getExisted(1L);
//        Set<String> tags=new HashSet<>();
//        tags.add("tag2");
//        task.setTags(tags);
//        taskRepository.save(task);
//        taskRepository.flush();
        MvcResult mvcRes=
        perform(MockMvcRequestBuilders.get("/api/tag/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0]").value("tag2"))
                .andReturn();
//        System.out.println("response from task1="+mvcRes.getResponse().getContentAsString());
//        Task task2=taskRepository.getExisted(1L);
//        System.out.println("response TASK_2 ="+task2.getTags());
    }

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "ADMIN")
//    @Transactional
    void shouldDeleteTagFromDataBase() throws Exception {
        //MvcResult mvcRes=
        perform(MockMvcRequestBuilders.delete("/api/tag/1")
                .contentType("application/json")
                .content("tag2"))
                .andExpect(status().isOk());
                //.andExpect(jsonPath("[0]").value("tag2"))
                //.andReturn();
        Task task = taskRepository.getExisted(1L);
        System.out.println("response delete"+task.getTags());
        assertTrue(task.getTags().isEmpty());
    }
    @Test
//    @Transactional
    @WithMockUser(username = "user1", password = "pwd", roles = "ADMIN")
    void shouldCreateTagInDataBase_createTag() throws Exception {
        //MvcResult mvcRes=
        perform(MockMvcRequestBuilders.post("/api/tag/1")
                .contentType("application/json")
                .content("tag3"))
                .andExpect(status().isOk());
                //.andExpect(jsonPath("[0]").value("tag2"))
                //.andReturn();
        Task task = taskRepository.getExisted(1L);
        System.out.println("response delete"+task.getTags());
        assertTrue(task.getTags().contains("tag3"));
    }
    @Test
//    @Transactional
    @WithMockUser(username = "user1", password = "pwd", roles = "ADMIN")
    void shouldCreateTagInDataBase_updateTag() throws Exception {
        //MvcResult mvcRes=
        perform(MockMvcRequestBuilders.patch("/api/tag/1")
                .contentType("application/json")
                .content("""
                        [ "tag2", "tag_update_patch" ]"""))
                .andExpect(status().isOk());
        Task task = taskRepository.getExisted(1L);
        System.out.println("response delete" + task.getTags());
        assertTrue(task.getTags().contains("tag_update_patch"));
    }
    @Test
//    @Transactional
    @WithMockUser(username = "user1", password = "pwd", roles = "ADMIN")
    void shouldCreateTagInDataBase_putSeveralTag() throws Exception {
        perform(MockMvcRequestBuilders.put("/api/tag/1")
                .contentType("application/json")
                .content("""
                        [ "tag4", "tag_putSeveral" ]
                        """))
                .andExpect(status().isOk());
        Task task = taskRepository.getExisted(1L);
        System.out.println("response putSeveral" + task.getTags());
        assertTrue(task.getTags().contains("tag4"));
        assertTrue(task.getTags().contains("tag_putSeveral"));
    }
}
