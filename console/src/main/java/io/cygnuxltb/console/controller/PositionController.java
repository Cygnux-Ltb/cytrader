package io.cygnuxltb.console.controller;

import io.mercury.common.http.MimeType;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/position", produces = MimeType.APPLICATION_JSON_UTF8)
public class PositionController {

    private static final Logger log = Log4j2LoggerFactory.getLogger(PositionController.class);







}
