package com.etec.curtaurl.models;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record URLRequest(String longUrl){}
