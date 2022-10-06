package com.odev.myestateagencyapi.sec.web;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.odev.myestateagencyapi.sec.JWTUtils;
import com.odev.myestateagencyapi.sec.entities.AppRole;
import com.odev.myestateagencyapi.sec.entities.AppUser;
import com.odev.myestateagencyapi.sec.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.odev.myestateagencyapi.util.Constants.CLIENT_BASE_URL;

@RestController
@AllArgsConstructor
@CrossOrigin(CLIENT_BASE_URL)
public class AccountRestController {

    private final AccountService accountService;

    @GetMapping(path = "/admin/users")
    public List<AppUser> allUsers(){
        return accountService.listUsers();
    }

    @PostMapping(path = "/admin/user")
    public AppUser saveUser(@RequestBody AppUser appUser){
        return accountService.addNewUser(appUser);
    }

    @PostMapping(path = "/admin/roles")
    public AppRole saveRole(@RequestBody AppRole appRole){
        return accountService.addNewRole(appRole);
    }

    @PostMapping(path = "/admin/addRoleToUser")
    public void addRoleToUser(@RequestBody RoleUserForm roleUserForm){
        accountService.addRoleToUser(roleUserForm.getUsername(), roleUserForm.getRoleName());
    }

}

@Data
class RoleUserForm{
    private String username;
    private String roleName;
}
