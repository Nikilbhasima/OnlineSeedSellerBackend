package DataTransferObject;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class SeedDataTransfer {
	private int id;
	private String name;
	private float price;
	private float quantity;
	private String description;
	private String img;
}
