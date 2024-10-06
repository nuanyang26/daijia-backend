package top.nuanyang26.daijia.model.entity.customer;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.nuanyang26.daijia.model.entity.base.BaseEntity;

@Data
@Schema(description = "CustomerCar")
public class CustomerCar extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Schema(description = "客户ID")
	private Long customerId;

	@Schema(description = "车牌号")
	private String license;

	@Schema(description = "车型")
	private String brand;

}