package com.swung0x48.librarymanager.services;

import com.swung0x48.librarymanager.domain.LibUser;
import com.swung0x48.librarymanager.exception.AuthException;

public interface IUserService {
    LibUser validateUser(String username, String password) throws AuthException;
    LibUser registerUser(String username, String password, String role) throws AuthException;
    String GenerateJwtToken(LibUser user);
}
