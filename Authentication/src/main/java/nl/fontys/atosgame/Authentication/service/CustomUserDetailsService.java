package nl.fontys.atosgame.Authentication.service;

import nl.fontys.atosgame.Authentication.model.GameMaster;
import nl.fontys.atosgame.Authentication.repository.GameMasterRepository;
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
    private GameMasterRepository gameMasterRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        GameMaster gameMaster = gameMasterRepository.findByEmail(email);
        if (gameMaster == null) {
            throw new UsernameNotFoundException("No game master found with email: " + email);
        }
        return new User(gameMaster.getEmail(), "", new ArrayList<>());
    }
}
