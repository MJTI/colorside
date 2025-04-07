package com.colorsideweb.colorsidefirstdev.dto;

import com.colorsideweb.colorsidefirstdev.repository.userRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
public class ResetUserService {

    private final userRepo userRepo;

}
