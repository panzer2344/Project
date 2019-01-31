package com.azino.project.model.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormItem {

    private MultipartFile avatar;

    private String name;

    private Double price;

    /*@Autowired
    @JsonIgnore
    private transient ImageService imageService = null;*/

    /*public Item toItem(User user){
        return new Item(name, imageService.save(avatar, user), price);
    }*/

}
