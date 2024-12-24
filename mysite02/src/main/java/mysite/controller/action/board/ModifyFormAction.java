package mysite.controller.action.board;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mysite.controller.action.ActionServlet;
import mysite.dao.BoardDao;
import mysite.vo.BoardVo;
import mysite.vo.UserVo;

import java.io.IOException;
import java.util.Objects;

public class ModifyFormAction implements ActionServlet.Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BoardVo boardVo = null;
        if (req.getParameter("id") != null) {
            String id = req.getParameter("id");
            boardVo = new BoardDao().findById(Long.parseLong(id));
        }

        UserVo userVo = null;
        if (req.getSession().getAttribute("authUser") != null) {
            userVo = (UserVo) req.getSession().getAttribute("authUser");
        }

        if (boardVo != null && userVo != null ) {
            if (Objects.equals(boardVo.getUser_id(), userVo.getId())) {
                req.setAttribute("board",boardVo);
                req.setAttribute("currentPage", req.getParameter("currentPage"));
                req.getRequestDispatcher("/WEB-INF/views/board/modify.jsp").forward(req, resp);
            }
        }
        else resp.sendRedirect(req.getContextPath() + "/user?a=loginform");
    }
}
