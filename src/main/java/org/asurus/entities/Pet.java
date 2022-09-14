package org.asurus.entities;


import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors (chain = true)
@Data
public class Pet {
    private int id;
    private Category category;
    private String name;
    private List<String> photoUrls;
    private List<Tag> tags;
    private String status;
}
