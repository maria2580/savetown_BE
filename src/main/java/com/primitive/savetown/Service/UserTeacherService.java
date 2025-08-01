package com.primitive.savetown.Service;

import com.primitive.savetown.DTO.LoginDTO;
import com.primitive.savetown.DTO.RegistUserDTO;
import com.primitive.savetown.Repository.UserPersonalInfo.UserTeacher;
import com.primitive.savetown.Repository.UserPersonalInfo.UserTeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserTeacherService {
    private final UserTeacherRepository userRepo;

    public UserTeacher getUserById(Long userId) {
        return userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public long createUser (RegistUserDTO registUserDTO){
        try {
            UserTeacher newUser = new UserTeacher();
            newUser.setLoginId(registUserDTO.getLoginId());
            newUser.setLoginPw(registUserDTO.getLoginPw());
            newUser.setFullName(registUserDTO.getFullName());
            newUser.setEmail(registUserDTO.getEmail());
            newUser.setPhoneNumber(registUserDTO.getPhoneNumber());
            newUser.setDateOfBirth(registUserDTO.getDateOfBirth());
            userRepo.save(newUser);
            return newUser.getUserId();
        }catch (Exception e){
            e.printStackTrace();
            return -1L;
        }

    }
    public Long loginUser(LoginDTO loginDTO){
        try {
            Optional<UserTeacher> users = userRepo.findByLoginId(loginDTO.getLoginId());
            boolean flag=users.isPresent();
            //flag가 true라면 존재하는 유저
            if(flag&&users.get().getLoginPw().equals(loginDTO.getLoginPw())){
                return users.get().getUserId();
            }
            else {
                return -1L;
            }
        }catch (Exception e){
            return -1L;
        }
    }

    public UserTeacher updateUser(Long userId, RegistUserDTO updatedUser) {
        UserTeacher user = getUserById(userId);
        user.setFullName(updatedUser.getFullName());
        user.setEmail(updatedUser.getEmail());
        user.setPhoneNumber(updatedUser.getPhoneNumber());
        return userRepo.save(user);
    }

    public void deleteUser(Long userId) {
        userRepo.deleteById(userId);
    }
}
