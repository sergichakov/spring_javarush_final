package com.javarush.jira.bugtracking.tag;

import com.javarush.jira.bugtracking.Handlers;
import com.javarush.jira.bugtracking.tag.to.TagTo;
import com.javarush.jira.bugtracking.task.Task;
import com.javarush.jira.bugtracking.task.TaskRepository;
import com.javarush.jira.bugtracking.task.to.TaskTo;
import com.javarush.jira.bugtracking.task.to.TaskToExt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.javarush.jira.bugtracking.ObjectType.TASK;
//import static com.javarush.jira.bugtracking.task.TaskUtil.makeActivity;

@RequiredArgsConstructor
@Service
public class TagService {
    private final Handlers.TaskHandler handler;

    //public List<TagDto> getTagsFromTask;

//    public List<TagDto> getTagForTaskId(Long id){
//        return taskRepository.findAllByTag(long tagId);
//    }
    @Transactional
    public void create(Long id, TagTo tagTo) throws Exception{
        Optional<Task> optionalTask=handler.getRepository().findFullById(id);//taskTo.getId());
        if(optionalTask.isEmpty()){
            throw new Exception("wrong Task id not registered");
        }
        Task task=optionalTask.get();
        Set<String> setOfTag=task.getTags();
        setOfTag.add(tagTo.getTag());
        task.setTags(setOfTag);
        handler.getRepository().save(task);
        //Task created = handler.createWithBelong(taskTo, TASK, "task_author");
        //activityHandler.create(makeActivity(created.id(), taskTo));
        //return created;
    }
    @Transactional
    public void update(Long id,String tagBefore,String tagAfter) throws Exception {
        Optional<Task> optionalTask=handler.getRepository().findFullById(id);//taskTo.getId());
        if(optionalTask.isEmpty()){
            throw new Exception("wrong Task id not registered");
        }
        Task task=optionalTask.get();
        Set<String> setOfTag=task.getTags();
        //setOfTag.removeIf()
        setOfTag.remove(tagBefore);//.toString());
        setOfTag.add(tagAfter);//.toString());
        handler.getRepository().save(task);
    }
    @Transactional
    public void delete(Long id,TagTo tagTo) throws Exception {
        Optional<Task> optionalTask=handler.getRepository().findFullById(id);
        if(optionalTask.isEmpty()){
            throw new Exception("wrong Task id not registered");
        }
        Task task=optionalTask.get();
        Set<String> setOfTag=task.getTags();
        setOfTag.remove(tagTo.getTag());
        handler.getRepository().save(task);
        handler.getRepository().flush();
    }
    @Transactional
    public void setSeveral(Long id, List<String> tagsList) throws Exception {
        Optional<Task> optionalTask=handler.getRepository().findFullById(id);
        if(optionalTask.isEmpty()){
            throw new Exception("wrong Task id not registered");
        }
        Task task=optionalTask.get();
        Set<String> tagsSet=new HashSet<>();
        tagsSet.addAll(tagsList);
        //tagsList.stream().map(i->tagsSet.addAll(i));
        //System.out.println("response put="+tagsList.size()+ " set="+tagsSet.size());
        task.setTags(tagsSet);
        handler.getRepository().save(task);
    }
    public Set<String> getTags(Long id) throws Exception {
        Optional<Task> optionalTask=handler.getRepository().findFullById(id);
        if(optionalTask.isEmpty()){
            throw new Exception("wrong Task id not registered");
        }
        Task task=optionalTask.get();
        Set<String> setOfTag=task.getTags();
        //TagTo tagTo=new TagTo();
        Set<TagTo> tagSet=new HashSet<>();
        setOfTag.stream().map(i->tagSet.add(new TagTo(i)));
        //return tagSet;
        return setOfTag;
    }
}
