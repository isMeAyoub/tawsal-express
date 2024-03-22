package com.simplon.parametre.service;

import com.simplon.parametre.dtos.request.GeneralRequestDto;
import com.simplon.parametre.dtos.response.GeneralResponseDto;

/**
 * This service layer interface defines business logic operations for General entities.
 * Provides methods for getting and updating general information.
 *
 * @Author: Ayoub Ait Si Ahmad
 */
public interface GeneralService {
    GeneralResponseDto getGeneralInstance();
    GeneralResponseDto updateGeneral(GeneralRequestDto generalRequestDto);
}
