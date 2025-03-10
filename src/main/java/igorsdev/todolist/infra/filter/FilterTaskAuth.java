
package igorsdev.todolist.infra.filter;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;
import java.util.Set;

import org.antlr.v4.runtime.atn.SemanticContext.OR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import igorsdev.todolist.user.UserModel;
import igorsdev.todolist.user.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var servletPath = request.getServletPath();

        // System.out.println(servletPath);
        if (servletPath.startsWith("/tasks/")) {

            var authorization = request.getHeader("Authorization");

            var authEncoded = authorization.substring("Basic".length()).trim();

            byte[] authDecoded = Base64.getDecoder().decode(authEncoded);

            var authString = new String(authDecoded);

            String[] credentials = authString.split(":");

            String username = credentials[0];
            String password = credentials[1];

            Optional<UserModel> searchUser = userRepository.findByUsername(username);
            if (searchUser.isEmpty()) {
                response.sendError(401);
            } else {
                var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), searchUser.get().getPassword());
                if (passwordVerify.verified) {
                    request.setAttribute("idUser", searchUser.get().getId());
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(401);
                }

            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

}