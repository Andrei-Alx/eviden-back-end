package nl.fontys.atosgame.Authentication.service;

import nl.fontys.atosgame.Authentication.model.GameMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private GameMasterService gameMasterService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        GameMaster gameMaster = gameMasterService.findGameMasterByEmail(email);
        if (gameMaster == null) {
            throw new UsernameNotFoundException("GameMaster not found with email: " + email);
        }
        return new User(gameMaster.getEmail(), "", new ArrayList<>());
    }
}
