package com.Shoots.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class BusinessInfo {
    private int b_info_idx;
    private int business_idx;
    private String parking;
    private boolean shower;
    private boolean lounge;
    private String equipment;
    private boolean cctv;
    private boolean kiosk;
    private String rental;
}
