package org.asu.cse545.group4.server.loginservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import static java.util.Collections.emptyList;
import org.asu.cse545.group4.server.loginservice.dao.UserDetailsDao;
import org.asu.cse545.group4.server.sharedobjects.TblUser;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
    private UserDetailsDao userDetailsDao;
    
	
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	System.out.println("inside loaduserbyname"+username);
        TblUser applicationUser = userDetailsDao.findUserByUsername(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        System.out.println("before return");
        return new User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
    }
}
