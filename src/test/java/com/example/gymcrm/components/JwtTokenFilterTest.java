package com.example.gymcrm.components;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.gymcrm.services.web.JwtTokenProvider;

@ExtendWith(MockitoExtension.class)
public class JwtTokenFilterTest {

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private UserDetailsService userDetailsService;

    @InjectMocks
    private JwtTokenFilter jwtTokenFilter;

    @AfterEach
    public void cleanup() {
        SecurityContextHolder.clearContext();
    }

    @BeforeEach
    public void cleanup2() {
        SecurityContextHolder.clearContext();
    }

    // Case 1: bearerToken is null
    @Test
    public void testDoFilterInternalWithNullToken() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        when(request.getHeader("Authorization")).thenReturn(null);

        jwtTokenFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain, atLeastOnce()).doFilter(request, response);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    // Case 2: bearerToken is an empty string
    @Test
    public void testDoFilterInternalWithEmptyToken() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        when(request.getHeader("Authorization")).thenReturn("");

        jwtTokenFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain, atLeastOnce()).doFilter(request, response);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    // Case 3: bearerToken has text, but it does not start with 'Bearer '
    @Test
    public void testDoFilterInternalWithNonBearerToken() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        when(request.getHeader("Authorization")).thenReturn("Not a Bearer token");

        jwtTokenFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain, atLeastOnce()).doFilter(request, response);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }
}