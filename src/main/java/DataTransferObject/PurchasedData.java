package DataTransferObject;

import java.time.LocalDate;

import com.seedSeller.model.Buyer;
import com.seedSeller.model.Seed;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchasedData {
	private int id;
	private String name;
	private int quantity;
	private float totalPrice;
	private LocalDate purchaseDate;
	private String location;
	private String status;
	private int user_id;
	private int seed_id;
	private byte[] img;
}
