/*
 * Copyright 2004-2013 ICEsoft Technologies Canada Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS
 * IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package org.icefaces.test.invalidate;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionInvalidationFilter implements Filter {

    private FilterConfig config;

    public void init(FilterConfig filterConfig) throws ServletException {
        config = filterConfig;
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
        Object sessInvalidationAttribute = null; //servletRequest.getAttribute(EnvUtils.SESSION_INVALIDATION);
        System.out.println("SessionInvalidationFilter.doFilter: " + sessInvalidationAttribute);
        if (sessInvalidationAttribute != null) {
            if (servletRequest instanceof HttpServletRequest) {
                HttpSession sess = ((HttpServletRequest) servletRequest).getSession(false);
                if (sess != null) {
                    sess.invalidate();
                    System.out.println("SessionInvalidationFilter.doFilter: INVALIDATING!!!!");
                } else {
                    System.out.println("SessionInvalidationFilter.doFilter: no active session");
                }
            } else {
                System.out.println("SessionInvalidationFilter.doFilter: servlet request is not HttpServletRequest");
            }
        }
    }

    public void destroy() {
    }
}
