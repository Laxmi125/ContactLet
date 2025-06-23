package in.contactlet.dto;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactResp{
	    private Long primaryContactId;
	    private List<String> emails;
	    private List<String> phoneNumbers;
	    private List<Long> secondaryContactId;

}
