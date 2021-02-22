package io.cygnus.service.dto.pack;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class InboxMessage {

	@Getter
	@Setter
	private String title;

	@Getter
	@Setter
	private String content;

}
