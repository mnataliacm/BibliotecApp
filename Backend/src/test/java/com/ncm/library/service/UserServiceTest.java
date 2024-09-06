package com.ncm.library.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ncm.library.entity.User;
import com.ncm.library.repository.UserRepository;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        User user1 = new User();
        User user2 = new User();
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        Iterable<User> users = userService.findAll();
        assertNotNull(users);
        assertEquals(2, ((Collection<?>) users).size());
    }

    @Test
    void testCount() {
        when(userRepository.count()).thenReturn(2L);

        long count = userService.count();
        assertEquals(2L, count);
    }

    @Test
    void testFindById() {
        User user = new User();
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.findById(1);
        assertTrue(foundUser.isPresent());
        assertEquals(user, foundUser.get());
    }

    @Test
    void testFindByEmail() {
        User user = new User();
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.findByEmail("test@example.com");
        assertTrue(foundUser.isPresent());
        assertEquals(user, foundUser.get());
    }

    @Test
    void testSave() {
        User user = new User();
        userService.save(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testDeleteById() {
        userService.deleteById(1);
        verify(userRepository, times(1)).deleteById(1);
    }

    @Test
    void testExistsById() {
        when(userRepository.existsById(1)).thenReturn(true);

        boolean exists = userService.existsById(1);
        assertTrue(exists);
    }

    @Test
    void testExistsByEmail() {
        when(userRepository.existsByEmail("test@example.com")).thenReturn(true);

        boolean exists = userService.existsByEmail("test@example.com");
        assertTrue(exists);
    }
}
