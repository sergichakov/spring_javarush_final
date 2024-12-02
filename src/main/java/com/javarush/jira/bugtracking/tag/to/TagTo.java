package com.javarush.jira.bugtracking.tag.to;

import com.javarush.jira.common.to.BaseTo;
import lombok.*;
import org.checkerframework.checker.units.qual.A;

import java.util.Set;

@Data
@RequiredArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class TagTo extends BaseTo {
    //private Set<String> set;
    private String tag;

}
