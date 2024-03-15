package com.openclassrooms.mddapi.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private Long id;
  private String mail;
  private String nickname;

  public JwtResponse(String accessToken, Long id, String mail, String nickname) {
    this.token = accessToken;
    this.id = id;
    this.mail = mail;
    this.nickname = nickname;
  }
}
