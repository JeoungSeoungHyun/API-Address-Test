package site.metacoding.api_address.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.metacoding.api_address.domain.UserRepository;
import site.metacoding.api_address.web.dto.JoinReqDto;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void 회원가입(JoinReqDto joinReqDto) {
        userRepository.save(joinReqDto.toEntity());
    }

}
