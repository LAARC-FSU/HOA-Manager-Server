package com.laarc.hoamanagerserver.api.dto.property;

import com.laarc.hoamanagerserver.api.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeedDocumentDTO {

    private String fileName;
    private UserDTO uploader;
    private LocalDateTime uploaded;

}
