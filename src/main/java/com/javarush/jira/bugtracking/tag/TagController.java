package com.javarush.jira.bugtracking.tag;

import com.javarush.jira.bugtracking.tag.to.TagTo;
import com.javarush.jira.bugtracking.task.TaskController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = TagController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TagController {
    public final static String REST_URL="/api/tag"; //{taskID}/";
    public final TagService tagService;
    @GetMapping("/{taskID}")
    public Set<String> getTags(@PathVariable Long taskID) throws Exception {
        //Set<TagTo> tags=tagService.getTags(taskID);
        Set<String> tags=tagService.getTags(taskID);
        return tags;
    }

    @DeleteMapping("/{taskID}")
    public void deleteTag(@PathVariable Long taskID, @RequestBody String tag) throws Exception {
        tagService.delete(taskID, new TagTo(tag));
    }

    @PostMapping("/{taskID}")
    public void createTag(@PathVariable Long taskID,@RequestBody String tag) throws Exception {
        tagService.create(taskID,new TagTo(tag));
    }
    @PatchMapping("/{taskID}")
    public void updateTag(@PathVariable Long taskID, @RequestBody List<String> listOfTags) throws Exception {
        tagService.update(taskID,listOfTags.get(0),listOfTags.get(1));
    }
    @PutMapping("/{taskID}")
    public void putServeral(@PathVariable Long taskID,@RequestBody List<String>listOfTags) throws Exception {
        tagService.setSeveral(taskID,listOfTags);
    }
}
