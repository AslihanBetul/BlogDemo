package com.abm.bloggerdemo.service;

import com.abm.bloggerdemo.dto.request.UserSaveRequestDto;
import com.abm.bloggerdemo.dto.response.UserFindAllResponseDto;
import com.abm.bloggerdemo.entity.User;
import com.abm.bloggerdemo.exception.BloggerDemoAppException;
import com.abm.bloggerdemo.exception.ErrorType;
import com.abm.bloggerdemo.mapper.UserMapper;
import com.abm.bloggerdemo.repository.UserRepository;
import com.abm.bloggerdemo.utility.ServiceManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class UserService extends ServiceManager<User,Long> {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    public void userSaveDto(UserSaveRequestDto dto) {
        userRepository.save(UserMapper.INSTANCE.userSaveRequestDtoToUser(dto));
    }

    public List<UserFindAllResponseDto> findUserDto() {
        List<UserFindAllResponseDto> userFindAllResponseDtos = new ArrayList<>();

        findAll().forEach(user -> {
            userFindAllResponseDtos.add(UserMapper.INSTANCE.userToUserFindAllResponseDto(user));
        });
        return userFindAllResponseDtos;
    }

    public UserFindAllResponseDto findUserDto2(String name, String lastname) {

        User byNameAndByLastname = userRepository.findByNameIgnoreCaseAndLastnameIgnoreCase(name, lastname);
        UserFindAllResponseDto userFindAllResponseDto = UserMapper.INSTANCE.userToUserFindAllResponseDto(byNameAndByLastname);

        return userFindAllResponseDto;
    }
     //belirli bir kullanıcının id'sine göre detaylarını getiren metod:
     public UserFindAllResponseDto findUserDtoID(Long id) {
         Optional<User> byId = userRepository.findById(id);
         if(byId.isEmpty()){
             throw new BloggerDemoAppException(ErrorType.USER_NOT_FOUND, "User not found");
         }
             UserFindAllResponseDto userFindAllResponseDto = UserMapper.INSTANCE.userToUserFindAllResponseDto(byId.get());
             return userFindAllResponseDto;


     }

    public String updateUser(Long id,String name,String lastName,String email,String password){
        Optional<User> byId = userRepository.findById(id);
        if(byId.isEmpty()){
            throw new BloggerDemoAppException(ErrorType.USER_NOT_FOUND, "User not found");
        }
        User user = byId.get();
        user.setName(name);
        user.setLastname(lastName);
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);
        return "Güncelleme Basarili";
    }

    public String deleteUser(Long id){
        Optional<User> byId = userRepository.findById(id);
        if(byId.isEmpty()){
            throw new BloggerDemoAppException(ErrorType.USER_NOT_FOUND, "User not found");
        }
        User user = byId.get();
        userRepository.delete(user);
        return "Kullanıcı silme işlemi  basarili";
    }


}
