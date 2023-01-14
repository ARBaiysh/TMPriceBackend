package kg.baiysh.TMPriceBackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {
    private String storehouseName;
    private String groupName;
    private String code1c;
    private String name;
    private String inStock;
    private String price;
}
