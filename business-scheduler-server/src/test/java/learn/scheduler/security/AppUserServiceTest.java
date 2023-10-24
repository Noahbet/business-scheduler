package learn.scheduler.security;


import learn.scheduler.data.AppUserRepository;
import learn.scheduler.domain.ActionStatus;
import learn.scheduler.domain.Result;
import learn.scheduler.models.AppUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class AppUserServiceTest {

    @MockBean
    AppUserRepository repository;

    @MockBean
    PasswordEncoder passwordEncoder;

    @Autowired
    AppUserService service;

    @Test
    void shouldThrowUsernameNotFoundExceptionForMissingUser() {
        assertThrows(UsernameNotFoundException.class, () -> {
            service.loadUserByUsername("missing_username");
        });
    }

    @Test
    void shouldAddValidUser() {
        AppUser expected = new AppUser(5, "test1@test.com", "hashed_password", true, List.of("USER"));

        when(passwordEncoder.encode(any())).thenReturn("hashed_password");
        when(repository.add(any())).thenReturn(expected);

        Result<AppUser> actual = service.add("test1@test.com", "P@ssw0rd!");

        assertTrue(actual.isSuccess());
        assertEquals(expected, actual.getPayload());
    }

    @Test
    void shouldNotAddNullUsername() {
        Result<AppUser> expected = makeResult("username is required", null);

        Result<AppUser> actual = service.add(null, "P@ssw0rd!");

        assertEquals(expected.getMessages(), actual.getMessages());
    }

    @Test
    void shouldNotAddBlankUsername() {
        Result<AppUser> expected = makeResult("username is required", null);

        Result<AppUser> actual = service.add("", "P@ssw0rd!");

        assertEquals(expected.getMessages(), actual.getMessages());
    }

    @Test
    void shouldNotAddNullPassword() {
        Result<AppUser> expected = makeResult("password is required", null);

        Result<AppUser> actual = service.add("username@example.com", null);

        assertEquals(expected.getMessages(), actual.getMessages());
    }

    @Test
    void shouldNotAddBlankPassword() {
        Result<AppUser> expected = makeResult("password is required", null);

        Result<AppUser> actual = service.add("username@example.com", "");

        assertEquals(expected.getMessages(), actual.getMessages());
    }

    @Test
    void shouldNotAddTooShortPassword() {
        Result<AppUser> expected = makeResult("password must be at least 8 character and contain a digit, a letter, and a non-digit/non-letter", null);

        Result<AppUser> actual = service.add("username@example.com", "P@ssw0r");

        assertEquals(expected.getMessages(), actual.getMessages());
    }

    @Test
    void shouldNotAddPasswordWithNoDigit() {
        Result<AppUser> expected = makeResult("password must be at least 8 character and contain a digit, a letter, and a non-digit/non-letter", null);

        Result<AppUser> actual = service.add("username@example.com", "P@ssword");

        assertEquals(expected.getMessages(), actual.getMessages());
    }

    @Test
    void shouldNotAddPasswordWithNoSpecialCharacter() {
        Result<AppUser> expected = makeResult("password must be at least 8 character and contain a digit, a letter, and a non-digit/non-letter", null);

        Result<AppUser> actual = service.add("username@example.com", "Passw0rd");

        assertEquals(expected.getMessages(), actual.getMessages());
    }

    @Test
    void shouldNotAddPasswordWithNoLetters() {
        Result<AppUser> expected = makeResult("password must be at least 8 character and contain a digit, a letter, and a non-digit/non-letter", null);

        Result<AppUser> actual = service.add("username@example.com", "9455702@");

        assertEquals(expected.getMessages(), actual.getMessages());
    }

    @Test
    void shouldNotAddDuplicateUsername() {
        AppUser existing = new AppUser(5, "existing@test.com", "password", true, List.of("USER"));

        when(repository.findByUsername("existing@test.com")).thenReturn(existing);

        Result<AppUser> expected = makeResult("the provided username already exists", null);

        Result<AppUser> actual = service.add("existing@test.com", "P@ssw0rd!");

        assertEquals(expected.getMessages(), actual.getMessages());
    }

    public static <T> Result<T> makeResult(String message, T payload) {
        Result<T> result = new Result<>();
        if (message != null) {
            result.addMessage(ActionStatus.INVALID, message);
        }
        result.setPayload(payload);
        return result;
    }
}