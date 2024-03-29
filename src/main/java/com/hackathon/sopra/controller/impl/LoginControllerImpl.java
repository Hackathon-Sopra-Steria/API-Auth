package com.hackathon.sopra.controller.impl;

import com.hackathon.sopra.controller.LoginController;
import com.hackathon.sopra.model.security.UserCredentials;
import com.hackathon.sopra.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;

@RestController
@Api(tags = { "Login" }, description = "Access API management resource")
@RequestMapping("/api")
public class LoginControllerImpl implements LoginController {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private LoginService loginService;

    @Autowired
    public LoginControllerImpl(LoginService loginService){
        this.loginService = loginService;
    }

    @Override
    @ApiOperation(value="Insert credentials ",notes="Insert login credentials.")
    @PostMapping(path="/login", produces = { MediaType.APPLICATION_JSON_VALUE },consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<String> login(@ApiParam(name="User credentials", value="User credentials",required = true) @RequestBody UserCredentials userCredentials) {
        logger.info(" -- POST  /login {}",userCredentials.getEmail());
        return new ResponseEntity<>(loginService.login(userCredentials), HttpStatus.OK);
    }
}
