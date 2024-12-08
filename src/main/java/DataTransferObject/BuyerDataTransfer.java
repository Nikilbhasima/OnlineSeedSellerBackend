package DataTransferObject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BuyerDataTransfer {
	private int id;
	private String username;
	private String phone_number;
	private String password;
	private String role;
}
