package com.anthonypoon.loginbackend.interfaces;

import com.anthonypoon.loginbackend.dto.LoginRequestDTO;
import com.anthonypoon.loginbackend.dto.LoginResponseDTO;
import com.anthonypoon.loginbackend.models.AppUser;
import com.anthonypoon.loginbackend.services.AppUserDetailService;
import com.anthonypoon.loginbackend.services.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final JwtTokenProvider provider;
    private final AppUserDetailService userDetailService;

    public LoginController(JwtTokenProvider provider, AppUserDetailService userDetailService) {
        this.provider = provider;
        this.userDetailService = userDetailService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> getJWTFromLogin(@RequestBody LoginRequestDTO request) {
        AppUser user = userDetailService.tryUsernamePassword(request.getUsername(), request.getPassword());
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        LoginResponseDTO dto = new LoginResponseDTO();
        dto.setAccessToken(this.provider.encodeJwt(user));
        return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
    }
}
