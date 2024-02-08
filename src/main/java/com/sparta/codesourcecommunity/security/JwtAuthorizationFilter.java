package com.sparta.codesourcecommunity.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.codesourcecommunity.common.CommonResponseDto;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j(topic = "jwt 검증 및 인가")
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final MemberDetailsService memberDetailsService;
    private final ObjectMapper objectMapper;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain)
        throws ServletException, IOException {
        String token = jwtUtil.resolveToken(request);

        if (Objects.nonNull(token)) { //token != null
            if (jwtUtil.validateToken(token)) {
                Claims info = jwtUtil.getUserInfoFromToken(token);

                // 인증정보에 유저정보(email) 넣기
                String email = info.getSubject();
                //username -> user 조회 -> userDetails 에 담고 -> authentication 의 principal 에 담고
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                UserDetails memberDetails = memberDetailsService.getMemberDetails(email);
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                    memberDetails, null);
                //-> securityContent 에 담고
                context.setAuthentication(authentication);
                //-> SecurityContextHolder 에 담고
                SecurityContextHolder.setContext(context);
                //-> 이제 @AuthenticationPrincipal 로 조회할 수 있음

            } else {
                // 인증정보가 존재하지 않을때
                CommonResponseDto commonResponseDto = new CommonResponseDto("토큰이 유효하지 않습니다.",
                    HttpStatus.BAD_REQUEST.value());
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType("application/json; charset=UTF-8");
                response.getWriter().write(objectMapper.writeValueAsString(commonResponseDto));
            }
        }

        filterChain.doFilter(request, response);
    }
}
