package com.test.hulkstore.service;

import com.test.hulkstore.exceptions.NotFoundException;
import com.test.hulkstore.model.Users;
import com.test.hulkstore.repository.UsersRepository;
import com.test.hulkstore.service.impl.UsersServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UsersServiceTest {
    @Mock
    private UsersRepository mockUsersRepository;

    private Users user;
    @InjectMocks
    private UsersServiceImpl mockUsersService;

    @BeforeEach
    public void setup() {
        user = new Users();
        user.setId(1L);
        user.setName("name");
        user.setLastname("lastname");
        user.setUsername("username");
        user.setRole("role");
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addUsersTest() {
        String result = mockUsersService.addUsers(user);
        verify(mockUsersRepository).addUsers(user);
        assertEquals("name", result);
    }

    @Test
    void getUserByIdTest() {
        when(mockUsersRepository.getUserById(1L)).thenReturn(user);
        Users result = mockUsersService.getUserById(1L);
        assertEquals(result, user);
    }

    @Test
    void getUserByIdNotFoundTest() {
        Long userId = 1L;
        when(mockUsersRepository.getUserById(userId)).thenReturn(null);
        assertThrows(NotFoundException.class, () -> {
            mockUsersService.getUserById(userId);
        });
    }
}
