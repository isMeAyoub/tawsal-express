package com.simplon.parametre.service;

import com.simplon.parametre.dtos.request.GeneralRequestDto;
import com.simplon.parametre.dtos.response.GeneralResponseDto;

public interface GeneralService {
    GeneralResponseDto getGeneralInstance();
    GeneralResponseDto updateGeneral(GeneralRequestDto generalRequestDto);
}
