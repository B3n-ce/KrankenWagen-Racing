package szfm.krankenwagenracing.admin_user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import szfm.krankenwagenracing.admin_user.dto.UserDto;
import szfm.krankenwagenracing.admin_user.model.User;
import szfm.krankenwagenracing.admin_user.repository.UserRepository;

@Service
public class UserServicempl implements UserService
{
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(UserDto userDto) {
        User user = new User(userDto.getEmail(),  userDto.getFullname(), passwordEncoder.encode(userDto.getPassword()), userDto.getRole());
        return userRepository.save(user);
    }

    @Override
    public void updateFullName(String email, String newFullName) {
        User user = userRepository.findByEmail(email); // E-mail alapján keressük a felhasználót
        if (user != null) {
            user.setFullname(newFullName); // Új név beállítása
            userRepository.save(user);    // Változások mentése
        }
    }
}
