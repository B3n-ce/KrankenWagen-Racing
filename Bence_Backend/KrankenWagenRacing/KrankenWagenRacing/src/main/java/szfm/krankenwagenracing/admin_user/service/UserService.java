package szfm.krankenwagenracing.admin_user.service;

import szfm.krankenwagenracing.admin_user.dto.UserDto;
import szfm.krankenwagenracing.admin_user.model.User;
import szfm.krankenwagenracing.admin_user.repository.TicketRepository;

public interface UserService
{
    User save(UserDto userDto);
    void updateFullName(String email, String newFullName);

    boolean emailExists(String email);
}
