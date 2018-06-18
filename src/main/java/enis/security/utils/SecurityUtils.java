package enis.security.utils;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
	public static boolean checkRole(String role){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return ((List)auth.getAuthorities()).get(0).toString().equals(role);
	}

	public static String getLoggedUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}
}
