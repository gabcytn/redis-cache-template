package com.gabcytyn.redis_demo.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PlayerDTO {
  @NotNull @NotBlank private String fullName;
  @NotNull private Integer teamId;

  public PlayerDTO(String fullName, Integer teamId) {
    this.fullName = fullName;
    this.teamId = teamId;
  }

  public PlayerDTO() {}

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public Integer getTeamId() {
    return teamId;
  }

  public void setTeamId(Integer teamId) {
    this.teamId = teamId;
  }

  @Override
  public String toString() {
    return "PlayerDTO{" + "fullName='" + fullName + '\'' + ", teamId=" + teamId + '}';
  }
}
