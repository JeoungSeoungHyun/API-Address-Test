package site.metacoding.login_system.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.login_system.domain.User;
import site.metacoding.login_system.domain.UserRepository;
import site.metacoding.login_system.handler.ex.CustomException;
import site.metacoding.login_system.web.dto.user.InfoUpdateReqDto;
import site.metacoding.login_system.web.dto.user.PasswordUpdateReqDto;
import site.metacoding.login_system.web.dto.user.UserDetailRespDto;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void 회원가입(User user) {
        // 패스워드 암호화
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);

        user.setPassword(encPassword);
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public boolean 유저네임중복체크(String username) {
        Optional<User> userOp = userRepository.FindByUsername(username);

        if (userOp.isPresent()) {
            return false;
        } else {
            return true;
        }
    }

    @Transactional(readOnly = true)
    public UserDetailRespDto 유저정보찾기(Integer userId, User principal) {

        // 1. 권한확인
        if (!mAuthCheck(userId, principal)) {
            throw new CustomException("권한이 없습니다");
        }

        // 2. 정보찾기
        User userEntity = mFindUserEntity(userId);

        // 3. Dto로 변환
        UserDetailRespDto updateRespDto = new UserDetailRespDto(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getAddress());

        return updateRespDto;
    }

    @Transactional
    public boolean 회원정보수정(Integer userId, User principal, InfoUpdateReqDto infoUpdateReqDto) {
        // 1. 권한확인
        if (!mAuthCheck(userId, principal)) {
            throw new CustomException("권한이 없습니다");
        }

        // 2. 정보찾기
        User userEntity = mFindUserEntity(userId);

        // 3. 정보수정
        userEntity.setAddress(infoUpdateReqDto.getAddress());

        return true;
    }

    @Transactional
    public boolean 회원정보수정(Integer userId, User principal, PasswordUpdateReqDto passwordUpdateReqDto) {
        // 1. 권한확인
        if (!mAuthCheck(userId, principal)) {
            throw new CustomException("권한이 없습니다");
        }

        // 2. 정보찾기
        User userEntity = mFindUserEntity(userId);

        // 3. 패스워드 암호화
        String rawPassword = passwordUpdateReqDto.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);

        // 4. 정보수정
        userEntity.setPassword(encPassword);

        return true;
    }

    private User mFindUserEntity(Integer userId) {
        Optional<User> userOp = userRepository.findById(userId);

        if (userOp.isPresent()) {
            User userEntity = userOp.get();
            return userEntity;
        }
        throw new CustomException("해당 회원정보가 없습니다.");
    }

    private boolean mAuthCheck(Integer userId, User principal) {
        if (userId == principal.getId()) {
            return true;
        }
        return false;
    }

}
