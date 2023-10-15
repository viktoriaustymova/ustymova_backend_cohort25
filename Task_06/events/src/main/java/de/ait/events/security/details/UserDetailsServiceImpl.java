package de.ait.events.security.details;

import de.ait.events.models.Participant;
import de.ait.events.repositories.ParticipantsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ParticipantsRepository participantsRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        /*
        Optional<Participant> participantOptional = participantsRepository.findByEmail(email);

        if (participantOptional.isPresent()){
            Participant participant = participantOptional.get();
            AuthenticatedUser authenticatedUser = new AuthenticatedUser(participant);
            return authenticatedUser;
        } else {
            throw new UsernameNotFoundException("User with email <" + email + ">not found")

        }
         */
        Participant participant = participantsRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email <" + email + ">not found"));
        return new AuthenticatedUser(participant);
    }
}
