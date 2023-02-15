package com.blog.blogappapi.services.impl;

import com.blog.blogappapi.config.AppConstants;
import com.blog.blogappapi.entity.Role;
import com.blog.blogappapi.entity.User;
import com.blog.blogappapi.exceptions.ResourceNotFoundException;
import com.blog.blogappapi.payloads.UserDto;
import com.blog.blogappapi.repository.RoleRepo;
import com.blog.blogappapi.repository.UserRepo;
import com.blog.blogappapi.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto registerNewUser(UserDto userDto) {

        User user = this.modelMapper.map(userDto, User.class);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
        user.getRoles().add(role);
        User savedUser=this.userRepo.save(user);

        return this.modelMapper.map(savedUser,UserDto.class);


    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user=this.dtoToUser(userDto);
        User savedUser=this.userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {

        User user= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());
        User updateUser=this.userRepo.save(user);
        UserDto userDto1=this.userToDto(updateUser);

        return userDto1;
    }

    @Override
    public UserDto getUserById(Integer userId) {

        User user= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));


        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> users=this.userRepo.findAll();

       List<UserDto> userDtos= users.stream().map(user ->this.userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        this.userRepo.delete(user);


    }

    public User dtoToUser(UserDto userDto){
        User user=this.modelMapper.map(userDto,User.class);
      /*  User user =new User();

        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());

        return user;*/

        return user;
    }



    public UserDto userToDto(User user){
        UserDto userDto=this.modelMapper.map(user,UserDto.class);
       /* UserDto userDto=new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setAbout(user.getAbout());
        userDto.setPassword(user.getPassword());
        return userDto;*/

        return userDto;

    }
}
