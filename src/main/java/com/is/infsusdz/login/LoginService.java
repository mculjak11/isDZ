package com.is.infsusdz.login;

import com.is.infsusdz.ads.CarFindAdRepository;
import com.is.infsusdz.users.CarFindUser;
import com.is.infsusdz.users.CarFindUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {

    private final CarFindUserRepository carFindUserRepo;

    public LoginService(CarFindUserRepository carFindUserRepo) {
        this.carFindUserRepo = carFindUserRepo;
    }

    public ResponseEntity verify(LoginData userLogin) {
        CarFindUser usr = carFindUserRepo.findCarFindUserByEmailAndPassword(userLogin.getEmail(),
                userLogin.getPassword());
        if (usr != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(usr.getUsername());
        } else {
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .body("user not found");
        }
    }

    public ResponseEntity signup(SignupData userSignup) {
        CarFindUser tmp = carFindUserRepo.findCarFindUserByUsername(userSignup.getUsername());
        if (tmp == null) {
            CarFindUser usr = new CarFindUser();
            usr.setUsername(userSignup.getUsername());
            usr.setEmail(userSignup.getEmail());
            usr.setPassword(userSignup.getPassword());
            Map<String, Object> usrInfo = new HashMap<>();
            usrInfo.put("name", userSignup.getName());
            usrInfo.put("surname", userSignup.getSurname());
            usrInfo.put("phoneNo", userSignup.getPhoneNo());
            Map<String, Object> placeInfo = new HashMap<>();
            placeInfo.put("country", userSignup.getCountry());
            placeInfo.put("city", userSignup.getCity());
            placeInfo.put("address", userSignup.getAddress());
            usrInfo.put("place", placeInfo);
            usr.setUserInfo(usrInfo);
            carFindUserRepo.save(usr);
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .body("signup ok");
        } else {
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .body("username taken");
        }
    }
}
