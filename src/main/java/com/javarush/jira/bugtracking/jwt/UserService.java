package com.javarush.jira.bugtracking.jwt;


import com.javarush.jira.login.AuthUser;
import com.javarush.jira.login.Role;
import com.javarush.jira.login.User;
import com.javarush.jira.login.internal.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    /**
     * Сохранение пользователя
     *
     * @return сохраненный пользователь
     */
    public User save(User user) {
        return repository.save(user);
    }


    /**
     * Создание пользователя
     *
     * @return созданный пользователь
     */
    public User create(User user) {
        if (repository.findByEmailIgnoreCase(user.getEmail()).isPresent()) {
            throw new RuntimeException("Пользователь с таким email уже существует");
        }
        return save(user);
    }

    /**
     * Получение пользователя по имени пользователя
     *
     * @return пользователь
     */
    public UserDetails getByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findByEmailIgnoreCase(email).orElse(null);
        if (user==null){
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        return new AuthUser(user);
    }

    /**
     * Получение пользователя по имени пользователя
     * <p>
     * Нужен для Spring Security
     *
     * @return пользователь
     */
    public UserDetailsService userDetailsService(){
        UserDetailsService getByUsername;
        getByUsername = email -> {
            try {
                return getByUsername(email);
            } catch (UsernameNotFoundException e) {
                throw new RuntimeException(e);
            }
        };
        return getByUsername;
    }

    /**
     * Получение текущего пользователя
     *
     * @return текущий пользователь
     */
    public UserDetails getCurrentUser() throws UsernameNotFoundException {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }


    /**
     * Выдача прав администратора текущему пользователю
     * <p>
     * Нужен для демонстрации
     */
    @Deprecated
    public void getAdmin() throws UsernameNotFoundException {
        var userDetails = getCurrentUser();

        User user=repository.findByEmailIgnoreCase(userDetails.getUsername()).orElse(null);
        if (user==null){
            return;
        }
        Set set=new HashSet();
        set.add(Role.ADMIN);
        user.setRoles(set);
        save(user);
    }
}