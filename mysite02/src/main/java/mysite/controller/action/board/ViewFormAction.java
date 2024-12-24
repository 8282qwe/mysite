package mysite.controller.action.board;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mysite.controller.action.ActionServlet;
import mysite.dao.BoardDao;
import mysite.vo.BoardVo;

import java.io.IOException;
import java.util.List;

public class ViewFormAction implements ActionServlet.Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        String currentPage = req.getParameter("currentPage");
        Cookie[] cookies = req.getCookies();
        Cookie viewCookie = null;

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("viewPage")) {
                viewCookie = cookie;
            }
        }

        if (viewCookie == null) {
            resp.addCookie(makeCookie(req.getContextPath(),"viewPage",Long.toString(id)));
            new BoardDao().updateViewById(id);
        }
        else {
            if (!List.of(viewCookie.getValue().split("_")).contains(id.toString())) {
                viewCookie.setValue(viewCookie.getValue()+"_"+id);
                new BoardDao().updateViewById(id);
            }
            resp.addCookie(viewCookie);
        }

        BoardVo vo = new BoardDao().findById(id);
        if (vo == null) {
            resp.sendRedirect(req.getContextPath() + "/board");
        }
        req.setAttribute("board", vo);
        req.setAttribute("currentPage", currentPage);

        req.getRequestDispatcher("/WEB-INF/views/board/view.jsp").forward(req, resp);
    }

    private Cookie makeCookie(String path, String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath(path);
        cookie.setMaxAge(60 * 60 * 24);
        return cookie;
    }
}
