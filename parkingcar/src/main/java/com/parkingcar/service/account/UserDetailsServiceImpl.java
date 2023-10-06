
package com.parkingcar.service.account;
import com.parkingcar.model.account.Account;
import com.parkingcar.repository.account.IAccountRepository;
import com.parkingcar.repository.account.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    IAccountRepository iAccountRepository;
    @Autowired
    IRoleRepository iRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // đầu tiên mình query xuống database xem có user  đó không
        Account accountUser = this.iAccountRepository.findAccountByUsername(username);

        //Nếu không tìm thấy User thì mình thông báo lỗi
        if (accountUser == null) {
            System.out.println("User not found! " + username);
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }


        // Khi đã có user rồi thì mình query xem user đó có những quyền gì (Admin hay User)
        // [ROLE_USER, ROLE_ADMIN,..]
//        List<String> roleNames = this.iRoleRepository.findAllById(.getRole().getId());
//        String role = accountUser.getRole().getRoleName();
        String role = accountUser.getRole().getName();


        // Dựa vào list quyền trả về mình tạo đối tượng GrantedAuthority  của spring cho quyền đó
        Set<GrantedAuthority> grantList = new HashSet<>();
        GrantedAuthority authority = new SimpleGrantedAuthority(role);
        grantList.add(authority);
        //Cuối cùng mình tạo đối tượng UserDetails của Spring và mình cung cấp cá thông số như tên , password và quyền
        // Đối tượng userDetails sẽ chứa đựng các thông tin cần thiết về user từ đó giúp Spring Security quản lý được phân quyền như ta đã
        // cấu hình trong bước 4 method configure
        UserDetails userDetails = (UserDetails) new User(accountUser.getUsername(),
                accountUser.getPassword(), accountUser.isStatus(), true, true, true, grantList);
        return userDetails;
    }
}