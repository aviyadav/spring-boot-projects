package org.sample.spring.boot.marketapi.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasketCreateRequestDTO {

    private List<Long> productIds;
}
