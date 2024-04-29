package com.abm.bloggerdemo.service;

import com.abm.bloggerdemo.dto.request.UserSaveRequestDto;
import com.abm.bloggerdemo.dto.response.UserFindAllResponseDto;
import com.abm.bloggerdemo.entity.User;
import com.abm.bloggerdemo.mapper.UserMapper;
import com.abm.bloggerdemo.repository.UserRepository;
import com.abm.bloggerdemo.utility.ServiceManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        UserFindAllResponseDto userFindAllResponseDto = UserMapper.INSTANCE.userToUserFindAllResponseDto(byId.get());
        return userFindAllResponseDto;
    }


}
