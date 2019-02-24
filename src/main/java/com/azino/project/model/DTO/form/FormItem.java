package com.azino.project.model.DTO.form;

import com.azino.project.model.Category;
import com.azino.project.model.Item;
import com.azino.project.validation.annotations.NotEmptyMultipartFile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormItem {

    @NotNull
    @NotEmptyMultipartFile
    private MultipartFile avatar;

    @Size(min = Item.NAME_MIN_LENGTH, max = Item.NAME_MAX_LENGTH)
    @NotNull
    private String name;

    @Range(min = Item.PRICE_MIN_VALUE, max = Item.PRICE_MAX_VALUE)
    @NotNull
    private BigDecimal price;

    @Range(min = Item.COUNT_IN_STOCK_MIN_VALUE, max = Item.COUNT_IN_STOCK_MAX_VALUE)
    @NotNull
    private Integer countInStock;

    @NotNull
    private Set<Category> categories;

    /*@Autowired
    @JsonIgnore
    private transient ImageService imageService = null;*/

    /*public Item toItem(User user){
        return new Item(name, imageService.save(avatar, user), price);
    }*/

}
