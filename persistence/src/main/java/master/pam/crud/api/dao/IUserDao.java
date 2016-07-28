package master.pam.crud.api.dao;

import master.pam.crosscutting.dto.api.IUserDto;

import java.lang.reflect.InvocationTargetException;

public interface IUserDao {

    public IUserDto getUser(String aEmail, String aPassword);

    public IUserDto insertUser(IUserDto aDto, String aPassword);

    public IUserDto update(IUserDto aDto) throws IllegalAccessException, InvocationTargetException;

    public IUserDto getUser(long aUserId);

    public IUserDto getUser(String aEmail);

    public IUserDto getUserByFoursquareId(String aUserFoursquareId);
}
