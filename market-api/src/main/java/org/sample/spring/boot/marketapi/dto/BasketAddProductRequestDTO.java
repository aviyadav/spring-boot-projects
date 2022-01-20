package org.sample.spring.boot.marketapi.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasketAddProductRequestDTO {

    @NotNull private Long productId;
}
