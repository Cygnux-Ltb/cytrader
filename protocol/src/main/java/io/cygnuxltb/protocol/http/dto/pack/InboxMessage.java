package io.cygnuxltb.protocol.http.dto.pack;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class InboxMessage {

    private String title;

    private String content;

}
